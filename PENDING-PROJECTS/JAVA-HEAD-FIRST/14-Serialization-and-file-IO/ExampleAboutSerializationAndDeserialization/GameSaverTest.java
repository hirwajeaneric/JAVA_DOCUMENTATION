package ExampleAboutSerializationAndDeserialization;

import java.io.*;

public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter one = new GameCharacter(50, "Elf", new String[]{"bow","sword","dust"});
        GameCharacter two = new GameCharacter(200, "Troll", new String[]{"bare hands", "big ax"});
        GameCharacter three = new GameCharacter(120,"Magician",new String[]{"spells","invisibility"});

        //Here will go the code that may change the state value of these characters.@interface
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/home/hirwa/NetBeansProjects/JAVA-HEAD-FIRST/14-Serialization-and-file-IO/ExampleAboutSerializationAndDeserialization/Game.ser"));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        one = null;
        two = null;
        three = null;

        //Now let's bring back our data

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("/home/hirwa/NetBeansProjects/JAVA-HEAD-FIRST/14-Serialization-and-file-IO/ExampleAboutSerializationAndDeserialization/Game.ser"));
            GameCharacter oneRestore = (GameCharacter) is.readObject();
            GameCharacter twoRestore = (GameCharacter) is.readObject();
            GameCharacter threeRestore = (GameCharacter) is.readObject();

            System.out.println("One's type: "+oneRestore.getType());
            System.out.println("Two's type: "+twoRestore.getType());
            System.out.println("Three's type: "+threeRestore.getType());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
