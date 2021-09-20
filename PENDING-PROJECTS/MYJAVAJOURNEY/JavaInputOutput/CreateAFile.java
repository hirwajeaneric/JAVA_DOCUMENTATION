import java.io.File;
import java.io.IOException;

public class CreateAFile {
    public static void main(String[] args) {
        try {
            File myObj = new File("/home/hirwa/NetBeansProjects/MYJAVAJOURNEY/JavaInputOutput/fileOne.txt");
            if(myObj.createNewFile()){
                System.out.println("File Created: "+myObj.getName());
            }else {
                System.out.println("File Already exists.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }    
    }
}
