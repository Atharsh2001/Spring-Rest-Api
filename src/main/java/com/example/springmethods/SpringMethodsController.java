package com.example.springmethods;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class SpringMethodsController {
    HashMap<String,String> customerDetails = new HashMap<String,String>();
    @GetMapping("/getDetails")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public HashMap getMethod(){
        return customerDetails;
    }

    @PostMapping("/postDetails")
    @ResponseStatus(code = HttpStatus.CREATED)
    public String postMethod(@RequestBody Postclass[] posts){
        for(Postclass post : posts){
            customerDetails.put(post.Name, post.Password);
        }
        return "Details were added";
    }


    @PutMapping("/updatePassword")
    @ResponseStatus(code=HttpStatus.ACCEPTED)
    public ResponseEntity<String> updateMethod(@RequestBody Postclass post){
        List listOfNames = new ArrayList(customerDetails.keySet());
        if(listOfNames.contains(post.Name)){
            customerDetails.put(post.Name,post.Password);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Password has been Updated");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Name not Found");
        }
    }

    @DeleteMapping("/delete/{toDeleteName}")
    public ResponseEntity<String> deleteMethod(@PathVariable String toDeleteName){
        List listOfNames = new ArrayList(customerDetails.keySet());
        if(listOfNames.contains(toDeleteName)){
            customerDetails.remove(toDeleteName);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("The "+toDeleteName+" has been Removed");
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entered Name has not Found");
        }
    }
}
