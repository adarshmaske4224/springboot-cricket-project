package com.example.cricket.service;

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

    public Player savePlayer(Player player) {
        log.info("In saveplayer method ");
        return crickRepo.save(player);
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



