package controllers;

import models.user;
import play.data.Form;
import play.data.validation.Constraints;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import views.html.dashboard;
import views.html.login;

import static play.data.Form.form;

public class Application extends Controller {

    @Security.Authenticated(Secured.class)
    public static Result index() {
        return redirect(controllers.routes.Application.dashboard());
    }

    public static Result login() {
        return ok(
                login.render(form(Login.class))
        );
    }

    public static Result dashboard(){
        return ok(dashboard.render());
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
                    controllers.routes.Application.dashboard()
            );
        }
    }

    public static class Login {
        @Constraints.Required
        public String email;
        public String password;
        public user.szKor uRole;

        public String validate() {
            if (user.authenticate(email, password) == null) {
                return "Invalid login";
            }
            return null;
        }
    }



    public static String getUserName(Http.Context ctx) {
        user crt = user.findByEmail(ctx.session().get("email"));
        if(crt != null) return crt.getName();
        else return "Not logged in";
    }
}
