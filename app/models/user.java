package models;

/**
 * Created by Szil on 2014.05.10..
 */
import com.avaje.ebean.annotation.EnumMapping;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "i_user")
public class user extends Model{

    @Id
    @Constraints.Required
    @Constraints.Email
    private String email;

    @Constraints.Required
    private String name;

    @Constraints.Required
    @Constraints.Min(5)
    private String password;

    @EnumMapping(nameValuePairs = "ADMINISZTRATOR=A,KONYVELO=K,SZAMLAZO=S")
    public enum szKor {
        ADMINISZTRATOR,
        KONYVELO,
        SZAMLAZO
    }

    @Constraints.Required
    private szKor uRole;

    public szKor getuRole() {
        return uRole;
    }

    public void setuRole(szKor uRole) {
        this.uRole = uRole;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // -- Contructor

    public user(String email, String name, String password, szKor uRole) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.uRole = uRole;
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
                .eq("uRole", szKor.ADMINISZTRATOR)
                .findUnique();
    }

    // -- Create a user
    public static user create(String email, String name, String password, szKor role) {
        if (email != "" && name != "" && password != "") {
            user newUser = new user(email, name, password, role);
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