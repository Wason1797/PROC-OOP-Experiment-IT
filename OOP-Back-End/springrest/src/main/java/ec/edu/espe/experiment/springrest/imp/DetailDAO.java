package ec.edu.espe.experiment.springrest.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ec.edu.espe.experiment.springrest.dao.IDetailDAO;
import ec.edu.espe.experiment.springrest.dao.IIngredientDAO;
import ec.edu.espe.experiment.springrest.dto.Detail;
import ec.edu.espe.experiment.springrest.dto.Ingredient;
import ec.edu.espe.experiment.springrest.model.DBDetailOrder;
import ec.edu.espe.experiment.springrest.model.DBDetailOrderPK;
import ec.edu.espe.experiment.springrest.model.DBIngredient;
import ec.edu.espe.experiment.springrest.repo.IDetailOrderRepo;
import ec.edu.espe.experiment.springrest.repo.IIngredientRepo;

@Repository
public class DetailDAO implements IDetailDAO {

    @Autowired
    private IDetailOrderRepo repoDetail;

    @Autowired
    private IIngredientDAO daoIngredient;

    @Autowired
    private IIngredientRepo repoIngredient;

    @Override
    public List<Detail> getIngredientList(Integer idOrder) {
        List<Detail> listDetail = new ArrayList<>();
        try {
            
            List<DBDetailOrder> listDBDetail = repoDetail.findAll();
            if (listDBDetail != null) {
                for (DBDetailOrder aux_detail : listDBDetail) {
                    if (aux_detail.getDetailOrderPK().getOrder_code() == idOrder) {
                        DBIngredient dbIngredient = repoIngredient.findById(aux_detail.getDetailOrderPK().getIng_code()).get();
                        Ingredient ingredient = daoIngredient.toIngredient(dbIngredient);
                        if (ingredient != null) {
                            Detail detail = new Detail(ingredient, aux_detail.getPriceIngredient());
                            listDetail.add(detail);
                        }
                    }
                }
            }
        } catch (Exception e) {
            listDetail = new ArrayList<>();
        }
        return listDetail;
    }

    @Override
    public List<Ingredient> post(Integer idOrder, List<Integer> list_ingredient){
        List<Ingredient> list_response = new ArrayList<>();
        try{
            for(Integer id : list_ingredient){
                Ingredient ingredient = postIngredient(idOrder, id);
                if(ingredient != null){
                    list_response.add(ingredient);
                }
            }
        } catch(Exception e){

        }
        return list_response;
    }

    private Ingredient postIngredient(Integer idOrder, Integer idIngredient){
        Ingredient response = new Ingredient();
        try {
            DBDetailOrder detailOrder = new DBDetailOrder();
            detailOrder.setDetailOrderPK(new DBDetailOrderPK(idIngredient, idOrder));
            DBIngredient dbIngredient = repoIngredient.findById(idIngredient).get();
            Ingredient ingredient = daoIngredient.toIngredient(dbIngredient);
            detailOrder.setPriceIngredient(ingredient.getPrice());            
            repoDetail.save(detailOrder);
            response = ingredient;

        } catch (Exception e) {
            response = null;
        }
        return response;
    }

}