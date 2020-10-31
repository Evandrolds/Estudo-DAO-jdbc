package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import medel.dao.Daofactory;
import medel.dao.DepartmentDao;
import model.enttities.Department;

/**
 *
 * @author Evandro
 */
public class Program2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        DepartmentDao departmentDao = Daofactory.createDepartmentDao();

        System.out.println("----Teste 1: Insert Department----");
        Department dep = new Department(null, "carros");
        departmentDao.insert(dep);
        System.out.println("Inserted new deparment and Id = " + dep.getId());

        System.out.println("\n----Teste 2: Delete Department Id----");
        System.out.println("Enter id department to Delete");
        int id = sc.nextInt();
        departmentDao.deleteById(id);
        System.out.println("Deleted department");

        System.out.println("\n----Teste 3: Update Department");
        Department dep1 = new Department();
        dep1 = departmentDao.findById(4);
        dep1.setName("Carros");
        departmentDao.update(dep1);
        System.out.println("Department Updated " + dep1.getId());

        System.out.println("\n----Teste 4: FindAll department----");

        List<Department> list = departmentDao.findAll();

        for (Department dep2 : list) {
            System.out.println(dep2);
        }

        System.out.println("\n----Teste 5: FindById department----");
        Department d = departmentDao.findById(4);
        System.out.println(d);

        sc.close();
    }
}
