package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
            pst = con.prepareStatement("select Seller.*,Department.Name as DepName " // buscando todos os nomes dos vendedores + o nomes dods departamentos
                    + "from seller inner join Department on seller.DepartmentId = " // o JOIN é para buscar os dados da duas tabelas
                    + "departmentId where seller.Id = ?");
            pst.setInt(3, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                Department dep = new Department();
                dep.setId(rs.getInt("DepartmentId"));
                dep.setName(rs.getString("DepName"));
                Seller seller = new Seller();
                seller.setId(rs.getInt("Id"));
                seller.setName(rs.getString("Name"));
                seller.setEmail(rs.getString("Email"));
                seller.setBaseSalary(rs.getDouble("BaseSalary"));
                seller.setBirthDate(rs.getDate("BirthDate"));
                seller.setDepartment(dep);
                return seller;
            }
            return null;
        } catch (SQLException ex) {
            throw new bd.Bd_Exception("Error in select Seller: " + ex.getMessage());
        } finally {

            bd.BD.coloseResulset(rs);
            bd.BD.coloseStatement(pst);

        }

    }

    @Override
    public List<Seller> findAll() {
        return null;
    }

}
