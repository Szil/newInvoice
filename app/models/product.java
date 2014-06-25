package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Gergo on 2014.06.24..
 */
@Entity
public class product extends Model {

    @Id
    @GeneratedValue
    private Integer prodId;

    private String sku;

    private String prodName;

    private String unitOfMeasure;

    private Double unitPrice;

    private Double taxPercent;

    @ManyToOne
    private Integer currency;
}
