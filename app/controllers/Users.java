package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import models.user;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.register;

import java.util.List;

/**
 * Created by Gergo on 2014.06.14..
 */
@Security.Authenticated(Secured.class)
public class Users extends Controller{

    public static Result index(){
        return ok(views.html.users.userList.render());
    }

    public static Result register() {
        return ok(
                register.render()
        );
    }

    public static Result createUser() {
        JsonNode registerForm = request().body().asJson();
        if (user.findByEmail(registerForm.get("email").asText()) != null) {
            flash("error", "User already exist/or form has errors");
            return badRequest(register.render());
        } else {
            session().clear();
            String email = registerForm.get("email").asText();
            String name = registerForm.get("name").asText();
            String password = registerForm.get("password").asText();
            user.szKor role = user.szKor.valueOf(registerForm.get("uRole").asText());
            user.create(email, name, password, role);
            flash("success", "User created");
            return ok();
        }
    }

    public static Result delete(String id){
        user.find.ref(id).delete();
        return ok();
    }

    public static Result users() {
        List<user> userList;
        userList = user.findAll();
        JsonNode users = Json.toJson(userList);
        if (userList.isEmpty()) {
            return badRequest("No users found");
        }
        return ok(users);
    }

    public static class Register {

        @Constraints.Required
        public String email;
        @Constraints.Required
        public String name;
        @Constraints.Required
        public String password;
        public user.szKor role;
    }
}
