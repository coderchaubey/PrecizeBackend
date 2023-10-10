package com.Precize.PrecizeAssignment.Services;

import com.Precize.PrecizeAssignment.Enums.Result;
import com.Precize.PrecizeAssignment.Models.SatResults;
import com.Precize.PrecizeAssignment.Repositories.SatResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SatResultService {

    @Autowired
    private SatResultRepository satResultRepository;


//    function for creation of users
    public String addUser(SatResults satResults)throws IllegalArgumentException{

        List<SatResults> allResults=satResultRepository.findAll();

        int rank=allResults.size()+1;
        if(!allResults.isEmpty()){
//            sorting in descending order
            allResults.sort((result1, result2) -> Integer.compare(result2.getScore(), result1.getScore()));

            for(SatResults results:allResults){
                if(satResults.getScore() > results.getScore()) {
                    rank=results.getRanking();
                    break;
                }
            }
        }else{
//            for new entry
            if(satResults.getScore()>30){
                satResults.setResult(Result.PASSED);
            }else {
                satResults.setResult(Result.FAILED);
            }
            satResults.setRanking(1);
            satResultRepository.save(satResults);
            return "User Saved Successfully";
        }



//        updating all the ranks of the user
        for (SatResults results:allResults){
            if(results.getRanking()>=rank){
                results.setRanking(results.getRanking()+1);
//                update the same in db
                satResultRepository.save(results);
            }
        }
//        after getting a rank set it
        satResults.setRanking(rank);

//        checking for the result
        if(satResults.getScore()>30){
            satResults.setResult(Result.PASSED);
        }else {
            satResults.setResult(Result.FAILED);
        }
//        saving in the db
        satResultRepository.save(satResults);

        return "User Saved Successfully";
    }


//    function to getAllUser
    public List<SatResults> getAllUser()throws IllegalArgumentException{
        List<SatResults> allResults=satResultRepository.findAll();
//        System.out.println(allResults);
        if(!allResults.isEmpty()) {
            return allResults;
        }else {
            throw new IllegalArgumentException("Sorry No user available");
        }
    }

//    function to get user by ranking
    public int getRank(String userName)throws IllegalArgumentException{

        SatResults user=satResultRepository.findByName(userName);
        if(user!=null)
        return user.getRanking();
        else throw new IllegalArgumentException("Please provide a valid User Name");
    }

//  function to update the score
    public String updateScore(String userName,int newScore)throws IllegalArgumentException{

        boolean flag=false;
        List<SatResults> allUpdatedResults=satResultRepository.findAllByOrderByScoreDesc();
        for (SatResults results:allUpdatedResults){
            if (results.getName().equalsIgnoreCase(userName)){
                flag=true;
                results.setScore(newScore);
                break;
            }
        }
//        if the score was updated then also updating the rank of all the user
        if(flag){
            int rank = 1;

            for (SatResults result : allUpdatedResults) {
                result.setRanking(rank);
                rank++;
                satResultRepository.save(result);
            }
            return "Updation is Successful";
        }
        else throw new IllegalArgumentException("Please provide a valid User Name");
    }

//    function to delete entry
    @Transactional
    public String deleteUser(String userName)throws IllegalArgumentException{
       int id = satResultRepository.deleteByName(userName);
       if(id!=0){
           int rank = 1;

           for (SatResults result : satResultRepository.findAllByOrderByScoreDesc()) {
               result.setRanking(rank);
               rank++;
               satResultRepository.save(result);
           }
       }
       else throw new IllegalArgumentException("Provide a valid User Name");
      return userName+" deleted successfully";
    }
}
