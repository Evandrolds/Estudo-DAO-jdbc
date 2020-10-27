package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import medel.dao.SellerDao;
import model.enttities.Department;
import model.enttities.Seller;

/**
 *
 * @author Evandro
 */
public class SellerDaoJDBC implements SellerDao {
public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
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
            pst = con.prepareStatement("Insert into seller set (Name,Email,BirthDate,BaseSalary,DepartmentId");
            pst.setString(1, "Sergio");
            pst.setString(2,"sergion@hormail.com");
            pst.setDate(3, new java.sql.Date(sdf.parse("22/08/2000").getTime()));
            pst.setDouble(4,2550);
            pst.setInt(5,4);
            
            pst.executeQuery();
            
            System.out.println("Dados Inserido com sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro na Inserção de dados!"+ ex.getMessage());
        } catch (ParseException ex) {
            System.out.println("Erro na Dada " + ex.getMessage());
    }
        finally{
            bd.BD.coloseStatement(pst);
            bd.BD.closeConnection();
        }
        
    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
           
            pst = con.prepareStatement("SELECT seller.*,department.Name as DepName " +
                                       "FROM seller INNER JOIN department " +
                                       "ON seller.DepartmentId = department.Id " +
                                       "WHERE seller.Id = ?");
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

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;
    }

    @Override
    public List<Seller> findAll() {
        return null;
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

}
