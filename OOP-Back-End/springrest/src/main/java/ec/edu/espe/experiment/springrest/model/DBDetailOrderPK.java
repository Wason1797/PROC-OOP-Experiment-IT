package ec.edu.espe.experiment.springrest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class DBDetailOrderPK implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @NotNull
    @Column(name = "ing_code")
    private int ing_code;

    @NotNull
    @Column(name = "order_code")
    private int order_code;

    public DBDetailOrderPK() {
    }

    public DBDetailOrderPK(int ing_code, int order_code) {
        this.ing_code = ing_code;
        this.order_code = order_code;
    }

    public int getIng_code() {
        return ing_code;
    }

    public void setIng_code(int ing_code) {
        this.ing_code = ing_code;
    }

    public int getOrder_code() {
        return order_code;
    }

    public void setOrder_code(int order_code) {
        this.order_code = order_code;
    }

    

    
}