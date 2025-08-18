package com.example.cricket.service;

import com.example.cricket.dto.CrickDto;
import com.example.cricket.entity.Player;
import com.example.cricket.repository.CrickRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CrickService {

    @Autowired
    CrickRepo crickRepo;

    public List<Player> getAllPlayersData() {
        List<Player> all = crickRepo.findAll();
        return all;
    }


    public CrickDto savePlayer(CrickDto dto) {

        log.info("In service ");
        //dto to entity
        Player p = new Player();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setAge(dto.getAge());
        p.setRole(dto.getRole());
        p.setTeam(dto.getTeam());
        p.setTotalRuns(dto.getTotalRuns());
        p.setWickets(dto.getWickets());

        Player save = crickRepo.save(p);

        dto.setId(save.getId());
        dto.setName(save.getName());
        dto.setAge(save.getAge());
        dto.setRole(save.getRole());
        dto.setTeam(save.getTeam());
        dto.setTotalRuns(save.getTotalRuns());
        dto.setWickets(save.getWickets());
        return dto;
    }

    public String deleteById(int id) {
        Optional<Player> byId = crickRepo.findById(id);
        if (byId.isPresent()) {
            crickRepo.deleteById(id);
            return "Deleted data of id "+ id + " ";
        } else {
            throw new RuntimeException("Nulll pointer");
        }
    }

    public Player updatePlayerData(int id, Player newData) {
        Optional<Player> byId = crickRepo.findById(id);
        if(byId.isPresent()){

           Player existing = byId.get();
            existing.setName(newData.getName());
            existing.setAge(newData.getAge());
            existing.setRole(newData.getRole());
            existing.setTeam(newData.getTeam());
            existing.setTotalRuns(newData.getTotalRuns());
            existing.setWickets(newData.getWickets());
            return crickRepo.save(existing);
        }else {
            throw new RuntimeException("Employee not found");
        }
    }

}



