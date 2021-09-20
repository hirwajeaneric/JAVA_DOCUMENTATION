/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package package1;

import java.util.*;

/**
 *
 * @author hirwa
 */
public class SortingAList {
    
    public static void main(String[] args){
        List<String> list1 = new ArrayList<String>();
        list1.add("Mango");
        list1.add("Apple");
        list1.add("Banana");
        list1.add("Grapes");
        
        //Sorting the list
        Collections.sort(list1);
        
        //Traversing list through the for-each loop
        for (String fruit : list1) {
            System.out.println(fruit);
        }
        
        System.out.println("Sorting numbers");
        //Creating a list of numbers
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(21);
        list2.add(11);
        list2.add(51);
        list2.add(1);
        
        //Sorting the list
        Collections.sort(list2);
        //Traversing list through the for-each loop
        for (Integer integer : list2) {
            System.out.println(integer);
        }
    }
}
