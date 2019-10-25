package ec.edu.espe.experiment.springrest.dto;

public class Size{
    private Integer _id;
    private String name;
    private Float price;

    public Size(){

    }

    public Size(Integer _id, String name, Float price){
        this._id = _id;
        this.name = name;
        this.price = price;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}