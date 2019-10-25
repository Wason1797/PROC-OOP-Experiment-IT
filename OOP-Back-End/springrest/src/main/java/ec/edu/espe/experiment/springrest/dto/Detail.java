package ec.edu.espe.experiment.springrest.dto;


public class Detail{
    private Ingredient ingredient;
    private Float ingredient_price;

    public Detail(){

    }

    public Detail(Ingredient ingredient, Float ingredient_price){
        this.ingredient = ingredient;
        this.ingredient_price = ingredient_price;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Float getIngredient_price() {
        return ingredient_price;
    }

    public void setIngredient_price(Float ingredient_price) {
        this.ingredient_price = ingredient_price;
    }

    
}