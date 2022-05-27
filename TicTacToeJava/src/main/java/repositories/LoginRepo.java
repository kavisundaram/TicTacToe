package repositories;
import models.LoginPage;
import java.util.List;

public interface LoginRepo
{
    public LoginPage login(String username);
}
