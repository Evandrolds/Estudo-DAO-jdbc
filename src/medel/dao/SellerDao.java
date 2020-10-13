package medel.dao;

import java.util.List;
import model.enttities.Seller;

/**
 *
 * @author Evandro
 */
public interface SellerDao {

    void insert(Seller obj);

    void update(Seller obj);

    void deleteById(Integer id);

    Seller findById(Integer id);

    List<Seller> findAll();
}
