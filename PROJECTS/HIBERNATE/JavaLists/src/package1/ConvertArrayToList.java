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
public class ConvertArrayToList {
    
    public static void main(String[] args){
        //Creating array
        String[] array = {"Java","Python","PHP","C++"};
        System.out.println("Printing Array: " +Arrays.toString(array));
        
        //Converting Array to List
        List<String> list = new ArrayList<String>();
        for (String lang : array) {
            list.add(lang);
        }
        System.err.println("Printing List: "+list);
    }
}
