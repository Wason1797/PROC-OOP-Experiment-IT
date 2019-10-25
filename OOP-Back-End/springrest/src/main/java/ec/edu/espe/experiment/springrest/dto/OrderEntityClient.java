package ec.edu.espe.experiment.springrest.dto;

import java.util.List;

public class OrderEntityClient{
    private String client_address;
    private String client_dni;
    private String client_name;
    private String client_phone;
    private List<Integer> ingredients;
    private Integer size;

    public OrderEntityClient(){
    }

    public String getClient_address() {
        return client_address;
    }

    public void setClient_address(String client_address) {
        this.client_address = client_address;
    }

    public String getClient_dni() {
        return client_dni;
    }

    public void setClient_dni(String client_dni) {
        this.client_dni = client_dni;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_phone() {
        return client_phone;
    }

    public void setClient_phone(String client_phone) {
        this.client_phone = client_phone;
    }

    public List<Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Integer> ingredients) {
        this.ingredients = ingredients;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }  
}