package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Gergo on 2014.06.24..
 */
@Entity
public class currency extends Model {

    @Id
    @GeneratedValue
    private Integer currencyId;

    private String currency_key;

    private String name;
}
