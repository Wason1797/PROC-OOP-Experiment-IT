package ec.edu.espe.experiment.springrest.dao;

import java.util.List;

import ec.edu.espe.experiment.springrest.dto.Order;
import ec.edu.espe.experiment.springrest.dto.OrderEntityClient;
import ec.edu.espe.experiment.springrest.model.DBOrder;


public interface IOrderDAO{
    public List<Order> getAll();
    public Order get(Integer id);
    public Order post(OrderEntityClient entity);
    public Order toOrder(DBOrder dbOrder);
}