package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Gergo on 2014.05.20..
 */
@Entity
public class organisation  extends Model {

    @Id
    @GeneratedValue
    private Integer orgId;

    private String name;

    private String bankAccount;

    private String taxId;

    private String phoneNumber;

    private String email;

}
