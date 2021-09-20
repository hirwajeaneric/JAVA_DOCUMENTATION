/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversionFromxToY;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author hirwa
 */

public class ConvertToByteArray {
    
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/home/hirwa/NetBeansProjects/04-Import-Export-Files/trying.txt");
        byte[] data = Files.readAllBytes(path);
    }
    
}

/**
 * Reading a file to byte array may be needed into various situations. 
 * For example, passing the information through the network as well as other 
 * APIs for further processing.
 */