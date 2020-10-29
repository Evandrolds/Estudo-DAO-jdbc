package application;

import java.util.Date;
import java.util.List;
import model.enttities.Seller;
import medel.dao.Daofactory;
import medel.dao.SellerDao;
import model.enttities.Department;


/*
 *
 * @author Evandro
 */
public class Program {

    public static void main(String[] args) {
        SellerDao sellerDao = Daofactory.createSellerDao();

        System.out.println("===  Teste 1: Seller FindById  ===");
        Seller seller = sellerDao.findById(4);
        System.out.println(seller);

        System.out.println("=== Teste 2: Seller Department ===");
        Department dep = new Department(4, null);
        List<Seller> list = sellerDao.findByDemarment(dep);
        for (Seller obj : list) {
            System.out.println(obj);
        }
        System.out.println("===  Teste 3: Seller FindAll  ===");
        list = sellerDao.findAll();
        for (Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("===  Teste 4: Seller Insert  ===");
        Seller seller1 = new Seller(null, " Sergio Lima da Silva", "sergio@hotmail.com", new Date(), 4250.00, dep);
        sellerDao.insert(seller1);
        System.out.println("Inserted! new Id "+ seller.getId());

    }
}
