package ec.edu.espe.experiment.springrest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.edu.espe.experiment.springrest.model.DBIngredient;

public interface IIngredientRepo extends JpaRepository<DBIngredient, Integer>{
    
}