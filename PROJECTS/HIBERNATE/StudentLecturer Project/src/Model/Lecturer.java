/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author hirwa
 */
@Entity
public class Lecturer {
    @Id
    private String lecturerId;
    private String lecturername;
    
    @OneToMany(mappedBy = "lecturer")
    private List<Student> students;
    
    public Lecturer() {
    }

    public Lecturer(String lecturerId, String lecturername) {
        this.lecturerId = lecturerId;
        this.lecturername = lecturername;
    }

    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturername() {
        return lecturername;
    }

    public void setLecturername(String lecturername) {
        this.lecturername = lecturername;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    

        
}
