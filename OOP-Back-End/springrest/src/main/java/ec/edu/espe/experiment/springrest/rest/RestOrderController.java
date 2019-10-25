package ec.edu.espe.experiment.springrest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.espe.experiment.springrest.dao.IOrderDAO;
import ec.edu.espe.experiment.springrest.dto.Order;
import ec.edu.espe.experiment.springrest.dto.OrderEntityClient;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@RequestMapping("/api/order")
public class RestOrderController{
    @Autowired
    private IOrderDAO dao;

    @GetMapping
    public List<Order> getAll() {        
        return dao.getAll();
    }

    @GetMapping(value = "/id/{id}")
    public Order get(@PathVariable("id") Integer id) {
        return new Order();
    }

    @PostMapping
    public Order post(@RequestBody OrderEntityClient entity) {
       return dao.post(entity);
    }
}