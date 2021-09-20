/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author The Crush
 */
@Entity
public class Picture implements Serializable {

    @Id
    private String name;
    private String photo;

    public Picture() {
    }

    public Picture(String name, String photo) {
        this.name = name;
        this.photo = photo;
    }


    public String getNames() {
        return name;
    }

    public void setNames(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
