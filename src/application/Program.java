package application;

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

        System.out.println("===  Teste 1: Seller FindById  === ");
        Seller seller = sellerDao.findById(4);
        System.out.println(seller);

        System.out.println("\n === Teste 2: Department FindByDepartment === ");
        Department dep = new Department(2, null);
        List<Seller> list = sellerDao.findByDemarment(dep);
        for (Seller seller1 : list) {
            System.out.println(seller1);

        }

        System.out.println("\n === Teste 3: Department FindAll === ");
        list = sellerDao.findAll();
        for (Seller seller2 : list) {
            System.out.println(seller2);
        }
    }
}
