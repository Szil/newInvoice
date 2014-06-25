package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by Gergo on 2014.06.11..
 */
@Entity
public class invoice extends Model {

    @Id
    private String invoiceId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "organisation", joinColumns = @JoinColumn(name = "orgId"))
    private Integer issuer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "partner", joinColumns = @JoinColumn(name = "partnerId"))
    private Integer partner;

    @ManyToOne(cascade = CascadeType.ALL)
    private Integer currency;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    private Date fullfilDate;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    private Date issueDate;

    @Formats.DateTime(pattern = "yyyy-MM-dd")
    private Date dueDate;

    private Integer numberOfCopies;

    @OneToMany(cascade = CascadeType.ALL)
    private List<products> productsList;
}
