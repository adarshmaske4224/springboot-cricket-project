package com.example.cricket.controller;


import com.example.cricket.entity.Player;
import com.example.cricket.service.CrickService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/apiController")
public class CrickController {

@Autowired
    private CrickService crickService;

    public CrickController(CrickService crickServiceervice){
        this.crickService = crickService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Player>> getPlayersData() {
        List<Player> allPlayersData = crickService.getAllPlayersData();
        return new ResponseEntity<>(allPlayersData, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Player> savePlayerData( @RequestBody Player player) {
        log.info("In postmapping method");
        Player player1 = crickService.savePlayer(player);
        log.info("return to postmapping");
        return new ResponseEntity<>(player1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlayerData(@PathVariable int id){
       String s = crickService.deleteById(id);
       return ResponseEntity.ok(s);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Player> updatePlayerData(@PathVariable int id, @RequestBody Player updatePlayer){
        Player p = crickService.updatePlayerData(id, updatePlayer);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }
}
