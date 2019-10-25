package ec.edu.espe.experiment.springrest.dao;

import java.util.List;

import ec.edu.espe.experiment.springrest.dto.Detail;
import ec.edu.espe.experiment.springrest.dto.Ingredient;


public interface IDetailDAO{
    public List<Detail> getIngredientList(Integer idOrder);
    public List<Ingredient> post(Integer idOrder, List<Integer> list_ingredient);
}