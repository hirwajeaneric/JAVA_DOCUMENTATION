/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author hp
 */
@Entity
@Table(name="departments")
public class Department implements Serializable {
    @Id
    @Column(name="id")
    int id;
    
    @Column(name="name")
    String name;
    @Column(name="manager_id")
    int mangerId;
    @Column(name="location_id")
    int locationId;

    public Department(int id, String name, int mangerId, int locationId) {
        this.id = id;
        this.name = name;
        this.mangerId = mangerId;
        this.locationId = locationId;
    }

    public Department() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMangerId() {
        return mangerId;
    }

    public void setMangerId(int mangerId) {
        this.mangerId = mangerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    
    
    
}
