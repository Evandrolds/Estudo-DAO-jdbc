package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.enttities.Department;


/**
 *
 * @author Evandro
 */
public class Application {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        Department obj = new Department(1, "Books");
        System.out.println(obj);
    }
}
