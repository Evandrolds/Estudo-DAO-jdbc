package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.enttities.Department;
import model.enttities.Seler;

/**
 *
 * @author Evandro
 */
public class Application {

    public static void main(String[] args) throws ParseException {

        Department obj = new Department(1, "Books");

        Seler seler = new Seler();
        seler.setId(21);
        seler.setName("Bob");
        seler.setEmail("Bob.seler@hotmail.com");
        seler.setBirthDate(new Date());
        seler.setBaseSalary(2500.00);
        seler.setDepartment(obj);
        System.out.println(seler);
    }
}
