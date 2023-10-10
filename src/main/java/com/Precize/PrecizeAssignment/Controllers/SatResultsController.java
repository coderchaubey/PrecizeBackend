package com.Precize.PrecizeAssignment.Controllers;

import com.Precize.PrecizeAssignment.Models.SatResults;
import com.Precize.PrecizeAssignment.Services.SatResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/satResult")
@CrossOrigin(origins = "http://localhost:3000/")
public class SatResultsController {

    @Autowired
    private SatResultService satResultService;

//    Api for inserting all the user data
    @PostMapping("/addUser")
    public ResponseEntity addUser(@RequestBody SatResults satResults){
            try{
                return new ResponseEntity(satResultService.addUser(satResults), HttpStatus.CREATED);
            }catch (IllegalArgumentException e){
                String response="User Not Added";
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
    }

//    Api for getting all the user data
    @GetMapping("/getAllUser")
    public ResponseEntity getAllUser(){
            try {
                return new ResponseEntity<>(satResultService.getAllUser(),HttpStatus.OK);
            }catch (IllegalArgumentException e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
    }

//    Api for getting rank
    @GetMapping("/getRanking/{userName}")
    public ResponseEntity getRanking(@PathVariable String userName){
            try
            {
                return new ResponseEntity<>(satResultService.getRank(userName),HttpStatus.OK);
            }catch (IllegalArgumentException e){
                return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
    }

//    Api for updating score
    @PutMapping("/updateScore")
    public ResponseEntity updateScore(@RequestParam("userName") String userName, @RequestParam("newScore") int newScore){
        try {
            return new ResponseEntity<>(satResultService.updateScore(userName,newScore),HttpStatus.ACCEPTED);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

//    Api for deleting Record
    @Transactional
    @DeleteMapping("/deleteUser/{userName}")
    public ResponseEntity deleteUser(@PathVariable String userName){
        try {
            return new ResponseEntity<>(satResultService.deleteUser(userName),HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
