package medel.dao;

import model.dao.Impl.SellerDaoJDBC;

/**
 *
 * @author Evandro
 */
public class Daofactory {
    // Fabrica de Dao
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC();
    }
}
