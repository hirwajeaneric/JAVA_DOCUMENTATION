package pojos;
// Generated Mar 9, 2021 12:13:02 PM by Hibernate Tools 4.3.1



/**
 * Location generated by hbm2java
 */
public class Location  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String type;
     private int parent;

    public Location() {
    }

    public Location(String name, String type, int parent) {
       this.name = name;
       this.type = type;
       this.parent = parent;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    public int getParent() {
        return this.parent;
    }
    
    public void setParent(int parent) {
        this.parent = parent;
    }




}

