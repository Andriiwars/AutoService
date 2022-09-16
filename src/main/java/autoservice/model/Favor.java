package autoservice.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favors")
public class Favor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Repairman repairman;
    @Column(name = "favor_name")
    private String favorName;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PAID,
        NOT_PAID
    }

    public Favor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Repairman getRepairman() {
        return repairman;
    }

    public void setRepairman(Repairman repairman) {
        this.repairman = repairman;
    }

    public String getFavorName() {
        return favorName;
    }

    public void setFavorName(String favorName) {
        this.favorName = favorName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public Favor setStatus(Status status) {
        this.status = status;
        return this;
    }
}
