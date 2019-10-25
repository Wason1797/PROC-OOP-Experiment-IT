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
public class DBSize{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "size_id")
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "size_name")
    private String name;

    @NotNull
    @Column(name = "size_price")
    private Float price;

    @OneToMany(mappedBy = "size")
    private List<DBOrder> orderList;

    public DBSize() {
    }

    public DBSize(Integer id) {
        this.id = id;
    }

    public DBSize(String name, Float price) {
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

    public List<DBOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<DBOrder> orderList) {
        this.orderList = orderList;
    }

    
}