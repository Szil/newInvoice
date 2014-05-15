package controllers;

import play.data.*;
import static play.data.Form.*;

import play.data.validation.Constraints;
import play.mvc.*;

import models.*;
import views.html.*;

public class Application extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return ok(views.html.index.render(getUserName(Http.Context.current())));
    }

    public static Result login() {
        return ok(
                login.render(form(Login.class))
        );
    }

    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return redirect(
                controllers.routes.Application.login()
        );
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    controllers.routes.Application.index()
            );
        }
    }

    public static Result register() {
        return ok(
                register.render(form(Register.class))
        );
    }

    public static Result createUser() {
        Form<Register> registerForm = Form.form(Register.class).bindFromRequest();
        if (registerForm.hasErrors() || user.findByEmail(registerForm.get().email) != null) {
            return badRequest(register.render(registerForm));
        } else {
            session().clear();
            String email = registerForm.get().email;
            String name = registerForm.get().name;
            String password = registerForm.get().password;
            user.create(email, name, password);
            flash("success", "You may login now.");
            return redirect(controllers.routes.Application.index());
        }
    }

    public static class Login {
        @Constraints.Required
        public String email;
        public String password;

        public String validate() {
            if (user.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }
    }

    public static class Register {

        @Constraints.Required
        public String email;
        public String name;
        public String password;
    }

    public static String getUserName(Http.Context ctx) {
        user crt = user.findByEmail(ctx.session().get("email"));
        if(crt != null) return crt.getName();
        else return "Not logged in";
    }
}
