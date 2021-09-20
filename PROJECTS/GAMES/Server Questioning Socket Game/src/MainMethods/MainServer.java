
package MainMethods;

import MainPackage.ServerSideView;
import java.awt.Color;


public class MainServer {
    public static void main(String args[]){
        ServerSideView window = new ServerSideView();
        window.getContentPane().setBackground(new Color(0, 51, 51));
        window.setVisible(true);
        
    }
}
