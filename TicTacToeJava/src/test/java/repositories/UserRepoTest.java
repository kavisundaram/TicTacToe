package repositories;

import models.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRepoTest
{
    UserRepo ur = new UserRepoImpl();

    public UserRepoTest()
    {

    }

    @Test
    public void getUser()
    {
        int id = 1;
        Users actual = this.ur.getUser(id);
        Users expected = new Users(1, "kavitha", "password", 11, 5, "jpg");
        Assertions.assertEquals(expected,actual);
    }
}
