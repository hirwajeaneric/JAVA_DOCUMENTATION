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
public class BookListExample {
    
    public static void main (String[] args){
        //Creating list of books
        List<Book> list = new ArrayList<Book>();
        //Creating books
        Book b1 = new Book(101,"Let us C","Hirwa Jean Eric","BPB",8);
        Book b2 = new Book(102,"The Davinci Code","Dan Brown","BPB",12);
        Book b3 = new Book(103,"Don Make Me Think","Damn","ABC",10);
        //Adding Books to list
        list.add(b1);
        list.add(b2);
        list.add(b3);
        //Traversing list
        for(Book b: list){
            System.out.println(b.id+" "+b.name+" "+b.author+" "+b.publisher+" "+b.quantity);
        }
    }
}
