package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import medel.dao.SellerDao;
import model.enttities.Department;
import model.enttities.Seller;

/**
 *
 * @author Evandro
 */
public class SellerDaoJDBC implements SellerDao {

    private Connection con;

    public SellerDaoJDBC(Connection con) { // para forçar a injeção de dependedncias
        this.con = con;
    }

    public SellerDaoJDBC() {

    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement("Insert into seller (Name,Email, "
                    + "BirthDate,BaseSalary,DepartmentId) Values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, obj.getName());
            pst.setString(2, obj.getEmail());
            pst.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            pst.setDouble(4, obj.getBaseSalary());
            pst.setInt(5, obj.getDepartment().getId());

            int rowsAfected = pst.executeUpdate();
            if (rowsAfected > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                bd.BD.coloseResulset(rs);
            } else {
                throw new bd.Bd_Exception("No rows afected");
            }

            System.out.println("Dados Inserido com sucesso!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            bd.BD.coloseStatement(pst);

        }

    }

    @Override
    public void update(Seller obj) {
        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement("UPDATE seller SET Name =?,Email =?,BirthDate =?,BaseSalary=?,DepartmentId=? WHERE Id =? ");
            pst.setString(1, obj.getName());
            pst.setString(2, obj.getEmail());
            pst.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            pst.setDouble(4, obj.getBaseSalary());
            pst.setInt(5, obj.getDepartment().getId());
            pst.setInt(6, obj.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            throw new bd.Bd_Exception("Erro, not data update " + e.getMessage());
        } finally {
            bd.BD.coloseStatement(pst);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement("delete from seller where Id =?");
            pst.setInt(1, id);
            pst.executeUpdate();

        } catch (SQLException ex) {
            throw new bd.Bd_Exception("Error,not was bosible file to delete: " + ex.getMessage());
        } finally {
            bd.BD.coloseStatement(pst);
        }

    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {

            pst = con.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE seller.Id = ?");
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                Department dep = instantiateDepartment(rs);
                Seller seller = instantiateSeller(rs, dep);
                return seller;
            }
            return null;
        } catch (SQLException ex) {

            throw new bd.Bd_Exception(ex.getMessage());
        } finally {

            bd.BD.coloseResulset(rs);
            bd.BD.coloseStatement(pst);

        }

    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "ORDER BY Name");
            rs = pst.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);

                }
                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller seller = new Seller();
        seller.setId(rs.getInt("Id"));
        seller.setName(rs.getString("Name"));
        seller.setEmail(rs.getString("Email"));
        seller.setBaseSalary(rs.getDouble("BaseSalary"));
        seller.setBirthDate(rs.getDate("BirthDate"));
        seller.setDepartment(dep);
        return seller;
    }

    @Override
    public List<Seller> findByDemarment(Department department) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement("SELECT seller.*,department.Name as DepName "
                    + "FROM seller INNER JOIN department "
                    + "ON seller.DepartmentId = department.Id "
                    + "WHERE DepartmentId = ? " + "ORDER BY Name");
            pst.setInt(1, department.getId());
            rs = pst.executeQuery();
            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();
            while (rs.next()) {
                Department dep = map.get(rs.getInt("DepartmentId"));
                if (dep == null) {
                    dep = instantiateDepartment(rs);
                    map.put(rs.getInt("DepartmentId"), dep);
                }

                Seller seller = instantiateSeller(rs, dep);
                list.add(seller);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(" Error na quuery:" + ex.getMessage());
        }
        return null;

    }

}
