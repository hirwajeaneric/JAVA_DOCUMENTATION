/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.util.*;
//import package1.Book;

/**
 *
 * @author hirwa
 */
public class HibernateListMapping {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*Implementation of arrayList */
        
        List<String> list = new ArrayList<String>();
        //Adding elements in the list
        list.add("Mango");
        list.add("Apple");
        list.add("Banana");
        list.add("Grapes");
        
        for (String fruit : list) {
            System.err.println(fruit);
        }
        
        /*
        List<String> list1 = new ArrayList<String>();
        List<Integer> list2 = new ArrayList<Integer>();
        List<Book> list3 = new ArrayList<Book>();
        */
    }
}
