package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Created by Gergo on 2014.06.24..
 */
@Entity
public class products extends Model{

    @OneToMany
    private Integer invoiceId;

    private Integer prodId;
}
