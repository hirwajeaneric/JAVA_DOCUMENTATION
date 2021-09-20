import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ThreeFileOperationsInOneProgram {
    public static void main(String[] args) {
        //createFile();
        //writeInTheFile();
        //readFromAFile();
        //getFileInfo();
        //deleteFile();
        //createFoldersAndDeleteOne();
    }

    public static void createFile(){
        File theFile = new File("/home/hirwa/NetBeansProjects/MYJAVAJOURNEY/JavaInputOutput/OneHundredJavaQuestions.txt");
        try {
            if(theFile.createNewFile()){
                System.out.println("File Created: "+theFile.getName());
            }else {
                System.out.println("\nFile Already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    public static void writeInTheFile() {
       try {
            FileWriter myWriter = new FileWriter("/home/hirwa/NetBeansProjects/MYJAVAJOURNEY/JavaInputOutput/OneHundredJavaQuestions.txt");
            String text = "This is my first day trying to learn myself under this discipline. I hope it will work. \n";
            myWriter.write(text);
            String additionalText = "Because, the problem here is, what would happen after transforming your schedules";
            myWriter.append(additionalText);
            myWriter.close();
            System.out.println("Successfully Wrote to the file.");
       } catch (Exception e) {
           System.out.println("An error occurred!");
           e.printStackTrace();
       }
    }

    public static void readFromAFile() {
        try {
            File theFile = new File("/home/hirwa/NetBeansProjects/MYJAVAJOURNEY/JavaInputOutput/OneHundredJavaQuestions.txt");
            Scanner reader = new Scanner(theFile);
            while (reader.hasNext()){
                String data = reader.nextLine();
                System.out.println("The text says: ");
                System.out.println(data);
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("An error occurred!");
            e.printStackTrace();
        }
    }

    public static void getFileInfo() {
        File theFile = new File("/home/hirwa/NetBeansProjects/MYJAVAJOURNEY/JavaInputOutput/OneHundredJavaQuestions.txt");
        if (theFile.exists()) {
            System.out.println("File name: "+theFile.getName());
            System.out.println("Absolute path: "+theFile.getAbsolutePath());
            System.out.println("Writeable: "+theFile.canWrite());
            System.out.println("Readable: "+theFile.canRead());
            System.out.println("File size in bytes "+theFile.length());
        } else {
            System.out.println("THe file does not exist!!");
        }
    }

    public static void deleteFile() {
        File theFile = new File("/home/hirwa/NetBeansProjects/MYJAVAJOURNEY/JavaInputOutput/Filetobedeleted.txt");
        if(theFile.exists()){
            if (theFile.delete()) {
                System.out.println("Deleted the file: "+theFile.getName());
            } else {
                System.out.println("Failed to delete the file!");
            }
        }else {
            System.out.println("The file you are trying to delete does not exist!!");
        }
    }

    public static void createFoldersAndDeleteOne() {
        File folder1 = new File("/home/hirwa/NetBeansProjects/MYJAVAJOURNEY/JavaInputOutput/Folder-1/");
        File folder2 = new File("/home/hirwa/NetBeansProjects/MYJAVAJOURNEY/JavaInputOutput/Folder-2/");
        try {
            if(folder1.createNewFile())
                System.out.println("Folder 1 Created: "+folder1.getName());
            if(folder2.createNewFile())
                System.out.println("Folder 2 Created: "+folder2.getName());
            
            if(folder1.delete())
                System.out.printf("Folder %s was deleted! ",folder1.getName());
            else
                System.out.println("Folder %s was not deleted! ");

            String dest = "/home/hirwa/";
            

        } catch (IOException e) {
            System.out.println("An error occurred! ");
            e.printStackTrace();
        }        

    }
}
