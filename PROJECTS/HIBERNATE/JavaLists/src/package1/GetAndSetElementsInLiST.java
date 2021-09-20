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
public class GetAndSetElementsInLiST {

    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        list.add("Mango");
        list.add("Apple");
        list.add("Banana");
        list.add("Grapes");
        
        //Accessing an element
        System.out.println("Returning an element: "+list.get(1));
        
        //Changing  the element
        list.set(1, "Pineapple");
        for(String fruit:list)
            System.out.println(fruit);
    }
}
