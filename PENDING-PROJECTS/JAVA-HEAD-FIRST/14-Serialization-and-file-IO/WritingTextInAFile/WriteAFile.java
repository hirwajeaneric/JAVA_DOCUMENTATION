package WritingTextInAFile;

import java.io.FileWriter;
import java.io.IOException;

public class WriteAFile {
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("/home/hirwa/NetBeansProjects/JAVA-HEAD-FIRST/14-Serialization-and-file-IO/WritingTextInAFile/File1.txt");
            writer.write("Hello. This is the first text that I am going to writer.");
            writer.close();
            System.out.println("File successfully Written!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
