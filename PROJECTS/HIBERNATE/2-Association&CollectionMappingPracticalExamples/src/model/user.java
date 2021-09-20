/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

    import java.util.ArrayList;
    import java.util.Collection;

    import javax.persistence.CascadeType;
    import javax.persistence.Entity;
    import javax.persistence.GeneratedValue;
    import javax.persistence.GenerationType;
    import javax.persistence.Id;
    import javax.persistence.JoinColumn;
    import javax.persistence.JoinTable;
    import javax.persistence.ManyToMany;
    import javax.persistence.OneToMany;
    import javax.persistence.OneToOne;
    import javax.persistence.Table;

/**
 *
 * @author hirwa
 */

@Entity
@Table(name="user_details")
public class user {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;
    private String username;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usr_vehicle", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
    private Collection<vehicle> vehicle = new ArrayList<>();
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id")
    private address address;
    
    @OneToMany(cascade = CascadeType.ALL)   
    @JoinTable(name = "user_mobile_mapping", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "mobile_id"))
    private Collection<mobile> mobile = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<vehicle> getVehicle() {
        return vehicle;
    }

    public void setVehicle(Collection<vehicle> vehicle) {
        this.vehicle = vehicle;
    }

    public address getAddress() {
        return address;
    }

    public void setAddress(address address) {
        this.address = address;
    }

    public Collection<mobile> getMobile() {
        return mobile;
    }

    public void setMobile(Collection<mobile> mobile) {
        this.mobile = mobile;
    }
    
    
}
