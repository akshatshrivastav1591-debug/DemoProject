package com.example.React_Spring.Security.SecurityService;

import com.example.React_Spring.Security.OwnerPojo.BikeOwnerPojoClass;
import com.example.React_Spring.Security.SecurityRepo.BIkeOwnerDetailsRepo;
import com.example.React_Spring.Security.UserPrinciple.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//What this class is about??
//Ans-->This is a custom implementation of UserDetailsService
//Spring Security uses this service to load user information from the database
//It is automatically detected as a bean because of @Service
//Spring Security never authenticates users directly from the database.Instead, it always calls UserDetailsService.
//Why UserDetailsService is important
//During authentication:
//User sends username + password
//Spring Security calls
//loadUserByUsername(username)
//This method must return a UserDetails object
//Spring compares passwords and grants or denies access
//This class connects Spring Security to your database.


@Service
public class BIkeOwnerDetailsServiceClass implements UserDetailsService {
    @Autowired
    BIkeOwnerDetailsRepo ownerepo;
//    BIkeOwnerDetailsRepo is a Spring Data JPA repository. Used to fetch user data from the database


    // Why this method exists??
// Ans--> This is the only method Spring Security calls to load users
//The parameter username comes from:
//Login request (Basic Auth / form login)
//JWT token subject
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("Before LoadBy user name method:");
    BikeOwnerPojoClass FetchedDetails=new BikeOwnerPojoClass();
    try {
        FetchedDetails=ownerepo.findByOwnername(username);
    }catch (Exception e){
        System.out.println(e.getLocalizedMessage());
    }
//    Queries the database using username
//    findByOwnername() is a custom JPA method
//    Fetches user details like:
//    username
//    password (hashed)
//    roles / authorities
    if(FetchedDetails==null){
        System.out.println("user not found:");
        throw new UsernameNotFoundException("user not found in the database");
    }

//    Why this is important
//    Spring Security expects this exception
//    If thrown:
//    Authentication immediately fails
//    Spring returns 401 Unauthorized
    System.out.println("Load by user name is Working:");
    return new UserPrinciple(FetchedDetails);
//    Why this is needed
//    Spring Security does not understand your entity
//    It only understands UserDetails
//    UserPrinciple is a custom adapter that:
//    Wraps BikeOwnerPojoClass
//    Implements UserDetails
//    This allows Spring Security to access:
//    getUsername()
//    getPassword()
//    getAuthorities()
//    account status flags
}
}






















