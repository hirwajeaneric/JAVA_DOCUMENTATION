import java.util.Scanner;

public class ClassesAndObjects {
    int x = 5;
    int size;
    public static void main(String[] args) {
        //This was an example of how classes and object work together in java
        /*
        ClassesAndObjects myObj1 = new ClassesAndObjects();//Object 1
        ClassesAndObjects myObj2 = new ClassesAndObjects();
        System.out.println(myObj1.x);
        System.out.println(myObj2.x+10);*/
        
        //Next is using multiple classes.@interface
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int size = reader.nextInt();
        System.out.println("\nEnter elements in the array: ");
        int[] array = new int[size];
        
        for (int i = 0; i < array.length; i++) {
            array[i] = reader.nextInt();
        }

        System.out.print("\n The result is: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]+" ");
        }

        //Another way to loop through any array using foreach.
        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.print("\n\n");
    }
}


