import java.io.File;
import java.util.Scanner;

public class ReadFiles {
    public static void main(String[] args) {
        try {
            File myObj = new File("/home/hirwa/NetBeansProjects/MYJAVAJOURNEY/JavaInputOutput/fileOne.txt");
            Scanner myReader = new Scanner(myObj);
            while(myReader.hasNext()){
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }
}
