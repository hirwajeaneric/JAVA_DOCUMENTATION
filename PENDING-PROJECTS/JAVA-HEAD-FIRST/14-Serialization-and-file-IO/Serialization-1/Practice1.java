import java.io.*;

public class Practice1 implements Serializable {
    public static void main(String[] args) {
        
        CharacterOne charone = new CharacterOne();
        charone.setPower(200);
        charone.setType("Spiderman");
        charone.setWeapon("Spiderwebs");

        CharacterTwo chartwo = new CharacterTwo();
        chartwo.setPower(140);
        chartwo.setType("Human");
        chartwo.setWeapon("Gun");

        //This is serialization
        try {
            FileOutputStream filestream = new FileOutputStream("MyGame.ser");
            ObjectOutputStream os = new ObjectOutputStream(filestream);
            os.writeObject(charone);
            os.writeObject(chartwo);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //And here we are going to deserialize.
        try {
            FileInputStream fileOutStream = new FileInputStream("MyGame.ser");
            ObjectInputStream is = new ObjectInputStream(fileOutStream);
            charone = (CharacterOne) is.readObject();
            chartwo = (CharacterTwo) is.readObject();

            System.out.println("Character one's type is "+charone.getType());
            System.out.println("Character two's type is "+chartwo.getType()+" and his weapon is "+chartwo.getWeapon());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}