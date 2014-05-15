package models;

/**
 * Created by Szil on 2014.05.10..
 */
import javax.persistence.*;

import play.data.format.Formats;
import play.data.validation.*;
import play.db.ebean.*;

import java.util.List;

@Entity
@Table(name = "Account")
public class user extends Model{

    private static final long serialVersionUID = 1L;

    @Id
    @Constraints.Required
    @Formats.NonEmpty
    private String email;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String password;

    // -- Contructor
    public user(String email, String name, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // -- Set/Get maybe removable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // -- Queries

    public static Model.Finder<String,user> find = new Model.Finder<String,user>(String.class, user.class);

    // -- All user
    public static List<user> findAll() {
        return find.all();
    }

    // -- Retrive user by email
    public static user findByEmail(String email) {
        return find.where().eq("email", email).findUnique();
    }

    // -- Authenticate a user
    public static user authenticate(String email, String password) {
        return find.where()
                .eq("email", email)
                .eq("password", password)
                .findUnique();
    }

    // -- Create a user
    public static user create(String email, String name, String password) {
        if (email != "" && name != "" && password != "") {
            user newUser = new user(email, name, password);
            newUser.save();
            return newUser;
        }
       else return null;
    }

    @Override
    public String toString() {
        return "User(" + email + ")";
    }
}