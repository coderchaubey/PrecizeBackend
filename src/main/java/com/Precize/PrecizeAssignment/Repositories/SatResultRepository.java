package com.Precize.PrecizeAssignment.Repositories;

import com.Precize.PrecizeAssignment.Models.SatResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SatResultRepository extends JpaRepository<SatResults,Integer> {

//    partial query
    SatResults findByName(String userName);
    List<SatResults> findAllByOrderByScoreDesc();
    int deleteByName(String userName);

}
