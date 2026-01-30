package com.example.React_Spring.Security.UserPrinciple;

import com.example.React_Spring.Security.OwnerPojo.BikeOwnerPojoClass;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
//What this class is
// A custom implementation of UserDetails
//Acts as an adapter between:
// Your database entity (BikeOwnerPojoClass)
//Spring Security’s authentication system
//Spring Security does NOT work with entities directly.
//It ONLY understands UserDetails.


public class UserPrinciple implements UserDetails {

    private  BikeOwnerPojoClass Owner;//Holds the user data fetched from database
    public UserPrinciple(BikeOwnerPojoClass fetchedDetails) {
        System.out.println("User Principle constructor called:");
        Owner=fetchedDetails;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }//return the Authority

    @Override //-->return user Password
    public @Nullable String getPassword() {
        return Owner.getOwnerpassword();
    }

    @Override //-->return userName
    public String getUsername() {
        return Owner.getOwnername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }//return whether the account is expired or not

    @Override
    public boolean isAccountNonLocked() {
        return true;
    } //-->the account is Locked or not

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

