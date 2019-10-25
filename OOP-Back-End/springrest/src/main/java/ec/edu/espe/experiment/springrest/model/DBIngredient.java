package ec.edu.espe.experiment.springrest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class DBIngredient{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ing_id")
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "ing_name")
    private String name;

    @NotNull
    @Column(name = "ing_price")
    private Float price;

    @OneToMany(mappedBy = "ingredient")
    private List<DBDetailOrder> detailOrderList;

    public DBIngredient() {
    }

    public DBIngredient(Integer id) {
        this.id = id;
    }

    public DBIngredient(String name, Float price) {
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<DBDetailOrder> getDetailorderList() {
        return detailOrderList;
    }

    public void setDetailorderList(List<DBDetailOrder> detailorder) {
        this.detailOrderList = detailorder;
    }
    
}