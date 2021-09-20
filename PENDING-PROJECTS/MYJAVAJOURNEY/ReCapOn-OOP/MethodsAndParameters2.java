import java.util.Scanner;

public class MethodsAndParameters2 {
    //To return the sum.
    public static void theMethod (int firstNumber, int secondNumber){
        int sum = firstNumber + secondNumber;
        System.out.printf("The sum of %d and %d is %d:",firstNumber,secondNumber,sum); 
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 2 numbers: ");
        int x = in.nextInt();
        int y = in.nextInt();
        
        theMethod(x, y);
        MethodsAndParameters mts = new MethodsAndParameters();
        System.out.println("\n");
        MethodsAndParameters.myMethod("Mugabo");
    }
}
