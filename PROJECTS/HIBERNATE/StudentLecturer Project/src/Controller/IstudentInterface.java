package Controller;

import java.util.List;
import Model.Student;

/**
 * @author hirwa
 */
public interface IstudentInterface {

    public Student save(Student st);

    public List<Student> stList();
    
    public List<Student> nameList();

    public List<Student> findNames (); 
}