package controllers;

import models.user;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.register;

import static play.data.Form.form;

/**
 * Created by Gergo on 2014.06.14..
 */
@Security.Authenticated(Secured.class)
public class Users extends Controller{

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

    public static class Register {

        @Constraints.Required
        public String email;
        public String name;
        @Constraints.Required
        public String password;
        public user.szKor role;
    }
}
