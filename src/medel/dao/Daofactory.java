package medel.dao;

import model.dao.Impl.DepartmentDaoJDBC;
import model.dao.Impl.SellerDaoJDBC;

/**
 *
 * @author Evandro
 */
public class Daofactory {
    // Fabrica de Dao
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC(bd.BD.getConnection());
    }
    public static DepartmentDao createDepartmentDao(){
        return new DepartmentDaoJDBC(bd.BD.getConnection());
    }
}
