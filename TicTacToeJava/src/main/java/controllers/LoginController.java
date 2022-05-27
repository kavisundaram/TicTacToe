package controllers;

import com.google.gson.Gson;
import models.LoginPage;
import io.javalin.http.Handler;
import io.javalin.http.Context;
import services.LoginService;


public class LoginController
{
    private LoginService ls;
    private Gson gson = new Gson();
    
    public LoginController(LoginService ls)
    {
        this.ls = ls;
    }
    
    public Handler loginuser = (context) ->
    {
        String username;
        username = context.pathParam("username");
        LoginPage l = ls.login(username);
        context.result(gson.toJson(l));
    };


    public void anotherHandle(Context context)
    {
        context.result("Another example");
    }
}
