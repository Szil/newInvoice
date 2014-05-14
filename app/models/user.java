package models;

/**
 * Created by Szil on 2014.05.10..
 */
import javax.persistence.*;

import play.data.validation.*;
import play.db.ebean.*;

import java.util.List;

@Entity
@Table(name = "User")
@SequenceGenerator(name = "user_seq", sequenceName = "user_seq")
public class user extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long uid;

    @Constraints.Required
    private String name;

    @Constraints.Required
    private String email;

    @Constraints.Required
    private String password;

    public Long getuid() {
        return uid;
    }

    public void setuid(Long uid) {
        this.uid = uid;
    }

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

    @Override
    public String toString() {
        return "Simple [uid=" + uid + ", name=" + name + "]";
    }

    public static Model.Finder<String,user> find = new Model.Finder<String,user>(String.class, user.class);

    public static user findById(Long uid) {
        return find.where().eq("uid", uid).findUnique();
    }

    public static user authenticate(String email, String password) {
        return find.where()
                .eq("email", email)
                .eq("password", password)
                .findUnique();
    }
}