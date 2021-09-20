import java.util.Scanner;

public class second {
    int biggest;
    //The biggest number.
    public void biggestnumber (int []array){
        for (int i = 0; i < array.length; i++) {
            if (array[i+1]>array[i]) {
                biggest = array[i+1];
                return biggest;
            } else {
                
            }
        }
    }

}
