package ec.edu.espe.experiment.springrest.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ec.edu.espe.experiment.springrest.dao.IIngredientDAO;
import ec.edu.espe.experiment.springrest.dto.Ingredient;
import ec.edu.espe.experiment.springrest.model.DBIngredient;
import ec.edu.espe.experiment.springrest.model.DBOrder;
import ec.edu.espe.experiment.springrest.repo.IIngredientRepo;





@Repository
public class IngredientDAO implements IIngredientDAO{

    @Autowired
    private IIngredientRepo repo;

    @Override
    public List<Ingredient> getAll(){
        List<Ingredient> list = new ArrayList<>();
        try{
            List<DBIngredient> list_dbIngredient = repo.findAll();
            if(list_dbIngredient != null){
                for(DBIngredient dbIngredient : list_dbIngredient){
                    list.add(toIngredient(dbIngredient));
                }
            }
        }
        catch(Exception e){
            list = new ArrayList<>();
            list.add(new Ingredient(1,e.toString(),(float)0));
        }
        return list;
    }

    @Override
    public Ingredient get(Integer id){
        Ingredient ingredient = new Ingredient();
        try{
             Optional<DBIngredient> aux_order = repo.findById(id);
            if (aux_order != null) {
                ingredient = toIngredient(aux_order.get());
                
            } else {
                ingredient = null;
            }
        }
        catch(Exception e){
            ingredient = null;
        }
        return ingredient;
    }

     

    @Override
    public Ingredient post(Ingredient ingredient){       
        Ingredient response = new Ingredient();
        try{
            DBIngredient dbIngredient = new DBIngredient(ingredient.getName(), 
                ingredient.getPrice());
            repo.save(dbIngredient);
            repo.flush();
            response = toIngredient(dbIngredient);
        }
        catch(Exception e){
            response = null;

        }
        return response;
    }

    @Override
    public Ingredient put(Ingredient ingredient){
        Ingredient response = null;
        try{
            Optional<DBIngredient> dbIngredient = repo.findById(ingredient.get_id());
            if(dbIngredient != null){
                DBIngredient aux = new DBIngredient();
                aux.setId(dbIngredient.get().getId());
                aux.setName(ingredient.getName() != null ? ingredient.getName() : dbIngredient.get().getName());
                aux.setPrice(ingredient.getPrice() != null ? ingredient.getPrice() : dbIngredient.get().getPrice());
                repo.save(aux);
                repo.flush();
                response = toIngredient(aux);
            }                  
        }
        catch(Exception e){
            response = null;
        }
        return response;
    }

    @Override
    public Ingredient toIngredient(DBIngredient dbIngredient){
        return new Ingredient(dbIngredient.getId(), 
            dbIngredient.getName(), 
            dbIngredient.getPrice());
    }
}