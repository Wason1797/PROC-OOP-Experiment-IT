package ec.edu.espe.experiment.springrest.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class DBOrder{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;
    
    @Size(max = 45)
    @Column(name = "order_name")
    private String name;

    @Size(max = 10)
    @Column(name = "order_dni")
    private String dni;

    @Size(max = 100)
    @Column(name = "order_address")
    private String address;

    @Size(max = 15)
    @Column(name = "order_phone")
    private String phone;
    
    @Column(name = "order_date")
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Column(name = "order_total")
    private Float total;
    
    @OneToMany(mappedBy = "order")
    private List<DBDetailOrder> detailOrderList;
    
    
    @JoinColumn(name = "dborder_id", referencedColumnName = "size_id")
    @ManyToOne
    private DBSize size;

    public DBOrder(){

    }

    public DBOrder(Integer id) {
        this.id = id;
    }

    public DBOrder(String name,String dni,
           String address,  String phone, Date date, Float total) {
        this.name = name;
        this.dni = dni;
        this.address = address;
        this.phone = phone;
        this.date = date;
        this.total = total;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public List<DBDetailOrder> getDetailOrderList() {
        return detailOrderList;
    }

    public void setDetailOrderList(List<DBDetailOrder> detailOrderList) {
        this.detailOrderList = detailOrderList;
    }

    public DBSize getSize() {
        return size;
    }

    public void setSize(DBSize size) {
        this.size = size;
    }

    

    
}