package controllers;

import com.google.gson.Gson;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import models.Users;
import services.UserService;

import java.util.List;


public class UserController
{
    private UserService us;
    private Gson gson = new Gson();

    public UserController(UserService us)
    {
        this.us = us;
    }

    public Handler getAllUsers = (context) ->
    {
        List<Users> user = us.getAllUsers();
        String usersJSON = gson.toJson(user);
        context.result(usersJSON);
    };

    public Handler getUser = (context) ->
    {
        int id = Integer.parseInt(context.pathParam("id"));
        Users u = us.getUser(id);
        context.result(gson.toJson(u));
    };

    public Handler regUser = (context) ->
    {
        Users u = gson.fromJson(context.body(), Users.class);
        u = us.regUser(u);
        context.result(gson.toJson(u));
    };

    public Handler updateWins = (context) ->
    {
        Users u = gson.fromJson(context.body(), Users.class);
        u = us.updateWins(u);
        context.result(gson.toJson(u));
    };

    public Handler updateLosses = (context) ->
    {
        Users u =gson.fromJson(context.body(), Users.class);
        u = us.updateLosses(u);
        context.result(gson.toJson(u));
    };

    public void anotherHandle(Context ctx)
    {
        ctx.result("Another example");
    }
}
