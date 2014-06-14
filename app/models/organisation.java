package models;

import play.data.format.*;
import play.data.validation.*;
import play.db.ebean.*;

import javax.persistence.*;


/**
 * Created by Gergo on 2014.05.20..
 */


@Entity
public class organisation  extends Model {

    @Id
    private Long orgId;

    @OneToMany(mappedBy = "user")
    private String owner;

    private String name;

    private Integer taxId;

    private Integer orgRegistationNum;

    private Integer EUTaxId;

    private String conMail;

    private String conPhone;

}
