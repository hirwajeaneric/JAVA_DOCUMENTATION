/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conversionFromxToY;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
/*
import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;
*/
/**
 *
 * @author hirwa
 */
public class ConvertInputStreamToString {
    
    public static void main(String[] args) throws Exception{
        
        InputStream inputStream = new FileInputStream(new File("C:/temp/test.txt"));
 /*
            ByteSource byteSource = new ByteSource() {
            @Override
            public InputStream openStream() throws IOException {
                return inputStream;
            }
        };
      
        String text = byteSource.asCharSource(Charsets.UTF_8).read();
         
        System.out.println(text);
*/   
}
    
}
