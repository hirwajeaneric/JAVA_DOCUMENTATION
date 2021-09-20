
package MainMethods;

import MainPackage.ClientSideView;
import java.awt.Color;


public class MainClient {
    public static void main(String args[]){
        ClientSideView window = new ClientSideView();
        window.getContentPane().setBackground(new Color(53,103,234));
        window.setVisible(true);
        
    }
}
