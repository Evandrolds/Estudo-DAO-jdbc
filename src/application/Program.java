package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
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
        Scanner sc = new Scanner(System.in);
        SellerDao sellerDao = Daofactory.createSellerDao();

        System.out.println("===  Teste 1: Seller FindById  ===");
        Seller seller = sellerDao.findById(4);
        System.out.println(seller);
        
        System.out.println("=== Teste 2: Seller FindByDepartment ===");
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
        System.out.println("Inserted! new Id " + seller.getId());
        

        System.out.println("===  Teste 5: Seller Update  ===");
        seller = sellerDao.findById(13);
        seller.setName("Evanilson Lima");
        sellerDao.update(seller);
        System.out.println("Updated completed");
        
        System.out.println("===  Teste 6: Seller DeleteById  ===");
        System.out.println("Enter Id for delete");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Id deleted "+ id);

    }
}
