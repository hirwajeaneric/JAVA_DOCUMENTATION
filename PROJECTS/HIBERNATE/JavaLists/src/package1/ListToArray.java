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
public class ListToArray {
    
    public static void main(String[] args){
        
        List<String> fruits = new ArrayList<>();
        fruits.add("Mango");
        fruits.add("Banana");
        fruits.add("Apple");
        fruits.add("Strawberry");
        
        String[] array = fruits.toArray(new String[fruits.size()]);
        System.out.println("Printing Array: "+Arrays.toString(array));  
        System.out.println("Printing List: "+fruits);  
    }
}
