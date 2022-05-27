package repositories;

import models.Users;
import java.util.List;

public interface UserRepo
{
    public Users getUser(int id);

    public List<Users> getAllUsers();

    public Users regUser(Users u);

    public Users updateWins(Users u);

    public Users updateLosses(Users u);
}
