package controllers;

import play.data.*;
import static play.data.Form.*;

import play.data.validation.Constraints;
import play.mvc.*;

import models.*;
import views.html.*;

public class Application extends Controller {
    
    public static Result index() {
        return ok(views.html.index.render("Hello Stranger!"));
    }

    public static Result login() {
        return ok(
                login.render(form(Login.class))
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
}
