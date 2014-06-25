package models;

import play.db.ebean.Model;

import javax.persistence.Entity;

/**
 * Created by Gergo on 2014.06.24..
 */
@Entity
public class products extends Model{

    private Integer invoiceId;

    private Integer prodId;
}
