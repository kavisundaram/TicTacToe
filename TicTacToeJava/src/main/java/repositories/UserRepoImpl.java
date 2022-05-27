package repositories;

import models.Users;
import util.JDBCConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepoImpl implements UserRepo
{
    public static Connection conn = JDBCConnection.getConnection();

    @Override
    public Users getUser(int id)
    {
        String sql = "Select * from users where u_id = ?";
        try
        {
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ps1.setInt(1,id);
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next())
            {
                return buildUser(rs1);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Users> getAllUsers()
    {
        String sql = "Select * from users";
        try
        {
            PreparedStatement ps1 = conn.prepareStatement(sql);
            ResultSet rs1 = ps1.executeQuery();
            List<Users> user = new ArrayList<>();
            while(rs1.next())
            {
                user.add(buildUser(rs1));
            }
            return user;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users regUser(Users u)
    {
        String sql = "Insert into users values (?,?,?,null, null,?) returning *";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getU_id());
            ps.setString(2, u.getUsername());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getProfile_pic());

            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return buildUser(rs);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users updateWins(Users u)
    {
        try
        {
            String sql = "Update users set wins=? where username=? returning *";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,u.getWins());
            ps.setString(2, u.getUsername());

            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                return buildUser(rs);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Users updateLosses(Users u)
    {
        try
        {
            String sql = "Update users set losses=? where username=? returning *";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, u.getLosses());
            ps.setString(2, u.getUsername());

            ResultSet rs = ps.executeQuery();

            if(rs.next())
            {
                return buildUser(rs);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private Users buildUser(ResultSet rs1) throws SQLException
    {
        Users u = new Users();
        u.setU_id(rs1.getInt("u_id"));
        u.setUsername(rs1.getString("username"));
        u.setPassword(rs1.getString("password"));
        u.setWins(rs1.getInt("wins"));
        u.setLosses(rs1.getInt("losses"));
        u.setProfile_pic(rs1.getString("profile_pic"));

        return u;
    }
}
