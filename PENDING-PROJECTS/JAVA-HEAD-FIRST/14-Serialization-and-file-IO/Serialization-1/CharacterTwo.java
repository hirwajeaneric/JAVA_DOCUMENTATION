import java.io.*;

public class CharacterTwo implements Serializable{
    private int power;
    private String type;
    private String weapon;

    public CharacterTwo () {

    }

    public CharacterTwo(int power, String type, String weapon) {
        this.power = power;
        this.type = type;
        this.weapon = weapon;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public int getPower() {
        return power;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }
    public String getWeapon() {
        return weapon;
    }
}
