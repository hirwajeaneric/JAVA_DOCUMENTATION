/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Student;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Aime
 */
public class StudentControllerTest {
    
    public StudentControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of save method, of class StudentController.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Student cl = null;
        StudentController instance = new StudentController();
        instance.save(cl);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class StudentController.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Student cl = null;
        StudentController instance = new StudentController();
        instance.update(cl);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class StudentController.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Student cl = null;
        StudentController instance = new StudentController();
        instance.delete(cl);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stlist method, of class StudentController.
     */
    @Test
    public void testStlist() {
        System.out.println("stlist");
        StudentController instance = new StudentController();
        List<Student> expResult = null;
        List<Student> result = instance.stlist();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
