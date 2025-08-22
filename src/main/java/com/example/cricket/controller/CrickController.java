package com.example.cricket.controller;


import com.example.cricket.dto.CrickDto;
import com.example.cricket.entity.Player;
import com.example.cricket.service.CrickService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/apiController")
public class CrickController {

@Autowired
 CrickService crickService;

    public CrickController(CrickService crickService){
       this.crickService = crickService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Player>> getPlayersData() {
        List<Player> allPlayersData = crickService.getAllPlayersData();
        return new ResponseEntity<>(allPlayersData, HttpStatus.OK);
    }

    // Validations applayed

    @PostMapping("/saveValidation")
    public ResponseEntity<CrickDto> savePlayerData( @Valid @RequestBody CrickDto crickDto) {
        log.info("In postmapping method");
        CrickDto player1 = crickService.savePlayer(crickDto);
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
    // finding Players by pagination
    @GetMapping("/getByPagination")
    public Page<Player> getPlayersByPagination(@RequestParam int page,
                                               @RequestParam int size,
                                               @RequestParam String sortBy){
        log.info("Api calls to fetch Players");
        return crickService.findPlayerByPagination(page,size,sortBy);
    }

}
