package Controller;

import Model.Lecturer;
import java.util.List;

/**
 * 
 * @author hirwa
 */

public interface IlecturerInterface {

    public Lecturer save(Lecturer lc);

    public List<Lecturer> lcList();

}