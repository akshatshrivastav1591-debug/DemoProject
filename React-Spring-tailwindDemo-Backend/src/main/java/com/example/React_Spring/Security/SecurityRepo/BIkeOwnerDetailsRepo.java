package com.example.React_Spring.Security.SecurityRepo;

import com.example.React_Spring.Security.OwnerPojo.BikeOwnerPojoClass;
import org.springframework.data.jpa.repository.JpaRepository;


//What this interface is??
//This is a Spring Data JPA Repository
//It acts as the data access layer for BikeOwnerPojoClass
//Spring automatically creates the runtime implementation
//You never write SQL here
//This repository is responsible only for database operations, not security logic.
//By extending JpaRepository, you automatically get:save(),findById(),findAll(),delete(),update()

                                       //JpaRepository<Entity, ID>
public interface BIkeOwnerDetailsRepo  extends JpaRepository<BikeOwnerPojoClass,String> {

    BikeOwnerPojoClass findByOwnername(String username);
}
