package com.example.React_Spring.Security.SecurityService;

import com.example.React_Spring.Security.OwnerPojo.BikeOwnerPojoClass;
import com.example.React_Spring.Security.SecurityRepo.BIkeOwnerDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class NewBikeOwnerDetailsServiceClass {
    @Autowired
    private BIkeOwnerDetailsRepo newEntry;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);



    public void addNewEntry(BikeOwnerPojoClass newEntryData) {

        newEntryData.setOwnerpassword(encoder.encode(newEntryData.getOwnerpassword()));
        newEntry.save(newEntryData);
    }
}
// The Service class that is used to save new user in the database:

