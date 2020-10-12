package application;

import java.text.ParseException;
import java.util.Date;
import medel.dao.Daofactory;
import medel.dao.SellerDao;
import model.enttities.Department;
import model.enttities.Seller;

/**
 *
 * @author Evandro
 */
public class Program {

    public static void main(String[] args) throws ParseException {

        Department obj = new Department(1, "Books");

        Seller seler = new Seller();
        seler.setId(21);
        seler.setName("Bob");
        seler.setEmail("Bob.seler@hotmail.com");
        seler.setBirthDate(new Date());
        seler.setBaseSalary(2500.00);
        seler.setDepartment(obj);
        System.out.println(seler);
        SellerDao sellerDao = Daofactory.createSellerDao();
    }
}
