package com.example.React_Spring.Security.OwnerPojo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "ownerdetails2")
public class BikeOwnerPojoClass {
    @Id
    private String ownername;

    private String ownerpassword;

}
// The modal class of our security Service class: which is used by repo to create table:

