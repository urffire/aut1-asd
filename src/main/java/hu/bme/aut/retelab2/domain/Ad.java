package hu.bme.aut.retelab2.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Ad {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String descr;
    private Integer price;
    private Date date;
    private String pw;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPassword() {
        return pw;
    }
    public void setPassword(String pw) {
        this.pw = pw;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date title) {
        this.date = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
}