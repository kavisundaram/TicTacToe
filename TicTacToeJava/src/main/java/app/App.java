package app;

import controllers.LoginController;
import controllers.UserController;
import io.javalin.Javalin;
import repositories.LoginRepo;
import repositories.LoginRepoImpl;
import repositories.UserRepo;
import repositories.UserRepoImpl;
import services.LoginService;
import services.LoginServiceImpl;
import services.UserService;
import services.UserServiceImpl;

import static io.javalin.apibuilder.ApiBuilder.path;
import static javax.swing.UIManager.get;

public class App
{
    public static void main(String[] args)
    {
        Javalin app = Javalin.create(config -> config.enableCorsForAllOrigins());
        establishRoutes(app);
        app.start();
    }

    private static void establishRoutes(Javalin app)
    {
        LoginRepo lr = new LoginRepoImpl();
        LoginService ls = new LoginServiceImpl(lr);
        LoginController lc = new LoginController(ls);

        app.get("/", new HelloHandler());
        app.get("/login/:username", lc.loginuser);


        UserRepo ur = new UserRepoImpl();
        UserService us = new UserServiceImpl(ur);
        UserController uc = new UserController(us);

        app.get("/users", uc.getAllUsers);
        app.get("/users/:id", uc.getUser);
        app.post("/users", uc.regUser);
        app.put("/users/wins", uc.updateWins);
        app.put("/users/lose", uc.updateLosses);


//        app.routes(() ->
//        {
//            path("/users", () ->
//            {
//                get(uc.getAllUsers);
//                path("id", () ->
//                {
//                   get(uc.getUser);
//                });
//            });
//        });

    }
}
