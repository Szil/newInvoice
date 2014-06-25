package models;

import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Gergo on 2014.06.24..
 */
@Entity
public class partner extends Model {

    @Id
    @GeneratedValue
    private Integer partnerId;

    private String name;

    private String bankAccount;

    private String taxId;

    private String phoneNumber;

    private String email;

}
