package com.example.React_Spring.Service;


import com.example.React_Spring.Repo.RepoClass;
import com.example.React_Spring.TestingClass.BikePojoClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceClass {
    @Autowired
    RepoClass repo;

    public List<BikePojoClass> getsdata() {
//      List<BikePojoClass> AllBikes= repo.findAll();
        return repo.findAll();

    }

    public BikePojoClass getSingleBikeDetails(int bikeid) {

       Integer id=bikeid;
        return  repo.findById(id).orElse(new BikePojoClass());
    }

    public void addnewbike(BikePojoClass newBike) {
        repo.save(newBike);
    }

    public String UpdateBike(BikePojoClass updateBike) {
       repo.save(updateBike);
       return "data is updated successfully";
    }

    public void DeleteBike(BikePojoClass deleteBike) {
        repo.delete(deleteBike);
    }
}
