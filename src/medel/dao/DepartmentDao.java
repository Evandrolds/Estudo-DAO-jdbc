package medel.dao;

import java.util.List;
import model.enttities.Department;


/**
 *
 * @author Evandro
 */
public interface DepartmentDao {
    // Implemantação do DAO do meu Department

    void insert(Department obj);

    void update(Department obj);

    void deleteById(Integer id);

    Department findById(Integer id);

    List<Department> findAll();
}
