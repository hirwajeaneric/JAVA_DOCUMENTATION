
package MainMethods;

import MainPackage.ServerSideView;
import java.awt.Color;


public class MainServer {
    public static void main(String args[]){
        ServerSideView window = new ServerSideView();
        window.getContentPane().setBackground(new Color(53,103,234));
        window.setVisible(true);
        
    }
}
