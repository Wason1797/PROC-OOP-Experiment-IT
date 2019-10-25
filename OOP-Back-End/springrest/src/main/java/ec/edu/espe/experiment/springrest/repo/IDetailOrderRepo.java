package ec.edu.espe.experiment.springrest.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.experiment.springrest.model.DBDetailOrder;
import ec.edu.espe.experiment.springrest.model.DBDetailOrderPK;

public interface IDetailOrderRepo extends JpaRepository<DBDetailOrder, DBDetailOrderPK>{
    
}