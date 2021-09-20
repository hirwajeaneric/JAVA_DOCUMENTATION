package conversionFromxToY;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.poi.util.IOUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hirwa
 */
public class ConvertingToInPutStreamExample {
    
    public static void main(String[] args) {
        String sampleString = "This is group 2";
        
        //Converting to input stream
        InputStream stream = new ByteArrayInputStream(sampleString.getBytes());
    }
}
