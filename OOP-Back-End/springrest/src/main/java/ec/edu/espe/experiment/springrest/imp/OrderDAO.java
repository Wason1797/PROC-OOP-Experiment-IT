package ec.edu.espe.experiment.springrest.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ec.edu.espe.experiment.springrest.dao.IDetailDAO;
import ec.edu.espe.experiment.springrest.dao.IOrderDAO;
import ec.edu.espe.experiment.springrest.dao.ISizeDAO;
import ec.edu.espe.experiment.springrest.dto.Ingredient;
import ec.edu.espe.experiment.springrest.dto.Order;
import ec.edu.espe.experiment.springrest.dto.OrderEntityClient;
import ec.edu.espe.experiment.springrest.dto.Size;
import ec.edu.espe.experiment.springrest.model.DBOrder;
import ec.edu.espe.experiment.springrest.repo.IOrderRepo;
import ec.edu.espe.experiment.springrest.repo.ISizeRepo;

@Repository
public class OrderDAO implements IOrderDAO {

    @Autowired
    private IOrderRepo repoOrder;

    @Autowired
    private ISizeRepo repoSize;

    @Autowired
    private IDetailDAO daoDetail;

    @Autowired
    private ISizeDAO daoSize;

    @Override
    public List<Order> getAll() {
        List<Order> list = new ArrayList<>();
        try {
            List<DBOrder> list_order = repoOrder.findAll();
            if (list_order != null) {
                for (DBOrder aux_order : list_order) {
                    Order order = new Order();
                    order = toOrder(aux_order);
                    list.add(order);
                }
            }
        } catch (Exception e) {
            list = new ArrayList<>();
        }
        return list;
    }

    public Order get(Integer id) {
        Order order = new Order();
        try {
            Optional<DBOrder> aux_order = repoOrder.findById(id);
            if (aux_order != null) {
                order = toOrder(aux_order.get());
            } else {
                order = null;
            }
        } catch (Exception e) {
            order = null;
        }
       
        return order;
    }

    public Order post(OrderEntityClient entity) {
        Order response = new Order();
        try {
            DBOrder dbOrder = new DBOrder();
            dbOrder.setName(entity.getClient_name());
            dbOrder.setAddress(entity.getClient_address());
            dbOrder.setDni(entity.getClient_dni());
            
            dbOrder.setPhone(entity.getClient_phone());
            dbOrder.setDate(new Date());
            dbOrder.setSize(repoSize.findById(entity.getSize()).get());
            repoOrder.save(dbOrder);
            repoOrder.flush();
            List<Ingredient> list_ingredient = daoDetail.post(dbOrder.getId(), entity.getIngredients());
            Size size = daoSize.get(dbOrder.getSize().getId());
            
            Float total_price = 0f;
            for(int i=0;i<list_ingredient.size();i++){
                total_price+=list_ingredient.get(i).getPrice();
            }
            total_price+=size.getPrice();
            dbOrder.setTotal(total_price);
            repoOrder.save(dbOrder);
            repoOrder.flush();
            response = get(dbOrder.getId());
        } catch (Exception e) {
            response = null;
        }
        return response;
    }

    @Override
    public Order toOrder(DBOrder dbOrder){
        Order order = new Order();
        order.set_id(dbOrder.getId());
        order.setClient_address(dbOrder.getAddress());
        order.setClient_dni(dbOrder.getDni());
        order.setClient_name(dbOrder.getName());
        order.setClient_phone(dbOrder.getPhone());
        order.setDate(dbOrder.getDate().toString());
        order.setTotal_price(dbOrder.getTotal());
        order.setDetail(daoDetail.getIngredientList(order.get_id()));
        order.setSize(daoSize.get(dbOrder.getSize().getId()));
        return order;
    }
}