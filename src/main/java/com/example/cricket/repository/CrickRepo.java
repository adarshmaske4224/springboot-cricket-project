package com.example.cricket.repository;

import com.example.cricket.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrickRepo extends JpaRepository<Player,Integer> {  //player is entity and Integer id id datatype

}
