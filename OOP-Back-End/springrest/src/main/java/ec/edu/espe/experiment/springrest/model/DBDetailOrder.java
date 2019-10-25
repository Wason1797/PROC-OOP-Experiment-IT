package ec.edu.espe.experiment.springrest.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DBDetailOrder{

    @EmbeddedId
    protected DBDetailOrderPK detailOrderPK;

    
    @Column(name = "detailorder_price_ingredient")
    private Float priceIngredient;

    @JoinColumn(name = "dborder_id", referencedColumnName = "order_id")
    @ManyToOne
    private DBOrder order;

    @JoinColumn(name = "dbingredient_id", referencedColumnName = "ing_id")
    @ManyToOne
    private DBIngredient ingredient;

    public DBDetailOrder() {
    }

    public DBDetailOrder(DBDetailOrderPK detalleOrdenPK) {
        this.detailOrderPK = detalleOrdenPK;
    }

    public DBDetailOrder(int idIngrediente, int idOrden) {
        this.detailOrderPK = new DBDetailOrderPK(idIngrediente, idOrden);
    }

    public DBDetailOrderPK getDetailOrderPK() {
        return detailOrderPK;
    }

    public void setDetailOrderPK(DBDetailOrderPK detailOrderPK) {
        this.detailOrderPK = detailOrderPK;
    }

    public Float getPriceIngredient() {
        return priceIngredient;
    }

    public void setPriceIngredient(Float priceIngredient) {
        this.priceIngredient = priceIngredient;
    }

    public DBOrder getOrder() {
        return order;
    }

    public void setOrder(DBOrder order) {
        this.order = order;
    }

    public DBIngredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(DBIngredient ingredient) {
        this.ingredient = ingredient;
    }

    
}