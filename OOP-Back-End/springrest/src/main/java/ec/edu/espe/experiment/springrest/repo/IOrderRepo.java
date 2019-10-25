package ec.edu.espe.experiment.springrest.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.experiment.springrest.model.DBOrder;

public interface IOrderRepo extends JpaRepository<DBOrder, Integer>{
    
}