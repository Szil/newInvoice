package models;

import javax.persistence.OneToOne;

/**
 * Created by Gergo on 2014.06.07..
 */
public class postalAddress {

    @OneToOne(mappedBy = "organisation")
    private Long organisation;

    private String city;

    private Integer zip;

    private String street;

    private String poBox;
}
