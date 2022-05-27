package repositories;
import models.LoginPage;
import util.JDBCConnection;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginRepoImpl implements LoginRepo
{
    public static Connection conn = JDBCConnection.getConnection();

    @Override
    public LoginPage login(String username)
    {
        String sql = "Select username, password from users where username = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return logindetails(rs);
            }
        }
        catch (SQLException e)
        {
           e.printStackTrace();
        }
        return null;
    }

    private LoginPage logindetails(ResultSet rs) throws SQLException
    {
        LoginPage lp = new LoginPage();
        lp.setUsername(rs.getString("username"));
        lp.setPassword(rs.getString("password"));
        return lp;
    }
}
