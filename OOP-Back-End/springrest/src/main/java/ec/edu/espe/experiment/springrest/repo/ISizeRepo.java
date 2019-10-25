package ec.edu.espe.experiment.springrest.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.experiment.springrest.model.DBSize;

public interface ISizeRepo extends JpaRepository<DBSize, Integer>{
    
}