/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author hirwa
 */
@Entity
public class Sectors {
    @Id
    private String id;
    private String name;
    private String District;

    public Sectors() {
    }

    public Sectors(String id, String name, String District) {
        this.id = id;
        this.name = name;
        this.District = District;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String District) {
        this.District = District;
    }
    
    
}
