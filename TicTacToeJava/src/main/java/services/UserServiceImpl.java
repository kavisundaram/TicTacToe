package services;

import models.Users;
import repositories.UserRepo;
import java.util.List;

public class UserServiceImpl implements UserService
{
    private UserRepo ur;
    public UserServiceImpl (UserRepo ur)
    {
        this.ur = ur;
    }

    @Override
    public Users getUser(int id)
    {
        return ur.getUser(id);
    }

    @Override
    public List<Users> getAllUsers()
    {
        return ur.getAllUsers();
    }

    @Override
    public Users regUser(Users u)
    {
        return ur.regUser(u);
    }

    @Override
    public Users updateWins(Users u)
    {
        return ur.updateWins(u);
    }

    @Override
    public Users updateLosses(Users u) {
        return ur.updateLosses(u);
    }
}
