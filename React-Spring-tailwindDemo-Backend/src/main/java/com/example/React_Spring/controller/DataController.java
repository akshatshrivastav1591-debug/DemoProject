package com.example.React_Spring.controller;


import com.example.React_Spring.Security.Jwt.JwtServiceClass;
import com.example.React_Spring.Security.OwnerPojo.BikeOwnerPojoClass;
import com.example.React_Spring.Security.SecurityService.NewBikeOwnerDetailsServiceClass;
import com.example.React_Spring.Service.ServiceClass;
import com.example.React_Spring.TestingClass.BikePojoClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class DataController {
    @Autowired
    private ServiceClass ser;
    @Autowired
    private NewBikeOwnerDetailsServiceClass newEntry;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtServiceClass jwtServiceClass;
    @GetMapping("/getdata")
    public List<BikePojoClass> showdata(){
        System.out.println("get Data Method called:");
        return  ser.getsdata();
    }
    @GetMapping("/getSingleData/{Bikeid}")
    public BikePojoClass ShowSingleData(@PathVariable("Bikeid")int Bikeid){

      return ser.getSingleBikeDetails(Bikeid);
    }
    @PostMapping("/getdata")
    public void AddNewBike( @RequestBody BikePojoClass NewBike){
        ser.addnewbike(NewBike);
    }
    @PutMapping("/getdata")
public String UpdateBike(@RequestBody BikePojoClass UpdateBike){
       return ser.UpdateBike(UpdateBike);

}
@DeleteMapping("/getdata")
public void DeleteBike(@RequestBody BikePojoClass DeleteBike){
        ser.DeleteBike(DeleteBike);
}

//Security
@PostMapping("/register")
    public String NewUser(@RequestBody BikeOwnerPojoClass newEntryData){
   newEntry.addNewEntry(newEntryData);
   return "Successfully Registered:";

}
@PostMapping("/login")
public String LoginUser (@RequestBody BikeOwnerPojoClass Owner){

       Authentication authorized=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(Owner.getOwnername(),Owner.getOwnerpassword()));
  if(authorized.isAuthenticated()){
     return jwtServiceClass.getJwtToken(Owner.getOwnername());
  }
  else{
      return "Authorized denied:";
  }

}

//    This login method handles user authentication in a stateless Spring Security + JWT setup:
//    it accepts the username and password from the request body,
//    wraps them in a UsernamePasswordAuthenticationToken,
//    and passes them to AuthenticationManager.authenticate(), which internally calls UserDetailsService and PasswordEncoder to verify credentials;
//    if authentication succeeds, a JWT token is generated for the authenticated username and returned to the client (usually as JSON),
//    and if credentials are invalid, Spring Security throws an AuthenticationException,
//    which is caught and results in a 401 Unauthorized response—this token is then sent by the client in the Authorization:
//    Bearer <token> header for all future protected requests, allowing the application to remain fully stateless.

@GetMapping("/testing")
public String Testing(){
    return "testing Succesfull";
}

}