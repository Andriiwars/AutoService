package autoservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Car car;
    @Column(name = "problem_description")
    private String problemDescription;
    @Column(name = "acceptance_date")
    private LocalDateTime acceptanceDate;
    @ManyToMany
    @JoinTable(
            name = "favors_orders",
            joinColumns = @JoinColumn(name = "favor_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Favor> favors;
    @ManyToMany
    @JoinTable(
            name = "goods_orders",
            joinColumns = @JoinColumn(name = "goods_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Goods> goods;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "final_price")
    private BigDecimal finalPrice;
    @Column(name = "end_date")
    private LocalDateTime endDate;

    public enum Status {
        IN_PROCESS,
        ACCEPTED,
        COMPLETED,
        FAILURE,
        PAID
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public LocalDateTime getAcceptanceDate() {
        return acceptanceDate;
    }

    public void setAcceptanceDate(LocalDateTime acceptanceDate) {
        this.acceptanceDate = acceptanceDate;
    }

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Favor> getFavors() {
        return favors;
    }

    public void setFavors(List<Favor> favors) {
        this.favors = favors;
    }
}

