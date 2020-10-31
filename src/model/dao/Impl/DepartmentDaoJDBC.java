package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import medel.dao.DepartmentDao;
import model.enttities.Department;

/**
 *
 * @author Evandro
 */
public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection con;

    public DepartmentDaoJDBC(Connection con) {
        this.con = con;
    }

    @Override
    public void insert(Department obj) {
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("Insert into Department (Name) values (?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, obj.getName());
            pst.executeUpdate();

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                bd.BD.coloseResulset(rs);
            } else {
                throw new bd.Bd_Exception("Unexpected error, no rows afected ");
            }
        } catch (SQLException e) {
            throw new bd.Bd_Exception("Error in Insert " + e.getMessage());
        } finally {
            bd.BD.coloseStatement(pst);

        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement("UPDATE department SET Name =? where Id =? ");
            pst.setString(1, obj.getName());
            pst.setInt(2, obj.getId());
            pst.executeLargeUpdate();

        } catch (SQLException e) {
            throw new bd.Bd_Exception(e.getMessage());
        } finally {
            bd.BD.coloseStatement(pst);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement("Delete from department where Id =?");
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new bd.BdIntegrityException(e.getMessage());
        } finally {
            bd.BD.coloseStatement(pst);
        }
    }

    @Override
    public Department findById(Integer id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("select  * from Department where Id = ?");

            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                Department dep = instatiationDep(rs);

                return dep;
            }

            return null;
        } catch (SQLException e) {
            throw new bd.Bd_Exception("Error in Select " + e.getMessage());
        } finally {
            bd.BD.coloseResulset(rs);
            bd.BD.coloseStatement(pst);
        }
    }

    @Override
    public List<Department> findAll() {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("SELECT * from department ORDER BY Id");

            rs = pst.executeQuery();
            List<Department> list = new ArrayList<>();
            while (rs.next()) {

                Department dep = new Department();

                dep = instatiationDep(rs);
                list.add(dep);

            }
            return list;

        } catch (SQLException e) {
            throw new bd.Bd_Exception(e.getMessage());
        } finally {
            bd.BD.coloseResulset(rs);
            bd.BD.coloseStatement(pst);
        }

    }

    public Department instatiationDep(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setName(rs.getString("Name"));
        dep.setId(rs.getInt("Id"));
        return dep;
    }

}
