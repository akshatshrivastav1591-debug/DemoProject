package com.example.React_Spring.TestingClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class BikePojoClass {
    @Id
    private  int bikeid;
    private  String bikeName;
    private  String bikeOwner;


//    public BikePojoClass(int bikeid, String bikeName, String bikeOwner) {
//        this.bikeid = bikeid;
//        this.bikeName = bikeName;
//        this.bikeOwner = bikeOwner;
//    }

    public int getBikeid() {
        return bikeid;
    }

    public void setBikeid(int bikeid) {
        this.bikeid = bikeid;
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public String getBikeOwner() {
        return bikeOwner;
    }

    public void setBikeOwner(String bikeOwner) {
        this.bikeOwner = bikeOwner;
    }

    @Override
    public String toString() {
        return "BikePojoClass{" +
                "bikeid=" + bikeid +
                ", bikeName='" + bikeName + '\'' +
                ", bikeOwner='" + bikeOwner + '\'' +
                '}';
    }
}
