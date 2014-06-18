package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.user;
import play.data.Form;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.register;

import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;

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
                register.render(form(Register.class))
        );
    }

    public static Result createUser() {
        Form<Register> registerForm = Form.form(Register.class).bindFromRequest();
        if (registerForm.hasErrors() || user.findByEmail(registerForm.get().email) != null) {
            flash("error", "User already exist/or form has errors");
            return badRequest(register.render(registerForm));
        } else {
            session().clear();
            String email = registerForm.get().email;
            String name = registerForm.get().name;
            String password = registerForm.get().password;
            user.szKor role = registerForm.get().role;
            user.create(email, name, password, role);
            flash("success", "User created");
            return redirect(controllers.routes.Application.dashboard());
        }
    }

    public static Result users() {

        ObjectMapper mapper = new ObjectMapper();
        List<user> userList = new ArrayList<>();
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
        public String name;
        @Constraints.Required
        public String password;
        public user.szKor role;
    }
}
