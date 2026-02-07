package com.example.React_Spring.Security.Jwt;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
//This class handles JWT creation, parsing, and validation
//Used by:Login controller (to generate token)
//JWT filter (to validate token)
@Service
public class JwtServiceClass {

//Stores the Base64-encoded secret key
// Used to:Sign JWT, Verify JWT

private String secretKey;

//Runs once when application starts
//Generates a random secret key
//⚠️Token becomes invalid if application restarts (important to remember)
public  JwtServiceClass(){
    secretKey=generateSecretKey();
}


//What it does:
//Creates a secure HMAC-SHA256 key
//Encodes it in Base64
// Returns it as a String
// Internal Flow
//KeyGenerator → cryptographic key generator
//HmacSHA256 → JWT signing algorithm
//Base64 encoding → safe storage format
public String generateSecretKey(){
    try {
        KeyGenerator keyGen=KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey1=keyGen.generateKey();
        System.out.println("Secret Key:"+secretKey1.toString());
        return Base64.getEncoder().encodeToString(secretKey1.getEncoded());

    }catch (NoSuchAlgorithmException e){
        throw  new RuntimeException("error generating Key:",e);
    }
}

    public String getJwtToken(String ownername) {
        Map<String,Object> claims=new HashMap<>();


//Verifies signature
//Throws exception if token is invalid or tampered
return Jwts.builder()
        .setClaims(claims)
        .setSubject(ownername)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
        .signWith(getKey(),SignatureAlgorithm.HS256).compact();
    }
    //-->ceating key
    private Key getKey(){
        byte[] keyBytes= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private  <T>T extractClaim(String token, Function<Claims,T>claimResolver){ //Storing extracted in the Claims object
         final  Claims claims=extractAllClaims(token);
         return  claimResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){ //--> extracting all the claims from the token
         return  Jwts.parserBuilder()
                 .setSigningKey(getKey())
                 .build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) { //--.extracting the username from the token
         return extractClaim(token,Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userdetails) { //validating token
         final  String userName=extractUsername(token);
        return (userName.equals(userdetails.getUsername())&&!IsTokenExpired(token));


    }

    private boolean IsTokenExpired(String token) { //-->checking whether the token is expired or not:
         return  extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) { //-->Extract Expiration date from the token
         return extractClaim(token,Claims::getExpiration);
    }
}
