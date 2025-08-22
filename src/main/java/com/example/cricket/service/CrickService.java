package com.example.cricket.service;

import com.example.cricket.Exception.PlayerNotFoundException;
import com.example.cricket.dto.CrickDto;
import com.example.cricket.entity.Player;
import com.example.cricket.repository.CrickRepo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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



    public String deleteById(int id) {
        Optional<Player> byId = crickRepo.findById(id);
        if (byId.isPresent()) {
            crickRepo.deleteById(id);
            return "Deleted data of id "+ id + " ";
        } else {
            throw new PlayerNotFoundException("Player Not Found with id "+ id);
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
            throw new PlayerNotFoundException("Employee not found with id "+ id);
        }
    }
    // DTO → Entity
    public static Player dtoToEntity(CrickDto dto) {
        Player p = new Player();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setAge(dto.getAge());
        p.setRole(dto.getRole());
        p.setTeam(dto.getTeam());
        p.setTotalRuns(dto.getTotalRuns());
        p.setWickets(dto.getWickets());
        return p;
    }

    // Entity → DTO
    public static CrickDto entityToDto(Player player) {
        CrickDto dto = new CrickDto();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setAge(player.getAge());
        dto.setRole(player.getRole());
        dto.setTeam(player.getTeam());
        dto.setTotalRuns(player.getTotalRuns());
        dto.setWickets(player.getWickets());
        return dto;
    }

    public CrickDto savePlayer(@Valid CrickDto crickDto) {
        Player player = CrickService.dtoToEntity(crickDto);
        Player save = crickRepo.save(player);
        CrickDto dto = CrickService.entityToDto(save);

        return dto;
    }

    public Page<Player> findPlayerByPagination(int page, int size, String sortBy) {
        log.info("Fetching Players - Page: {}, size :{}, sortBy: {}", page,size,sortBy);
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        Page<Player> pageOfPlayer = crickRepo.findAll(pageable);
        log.info("total Players found {} ",pageOfPlayer.getTotalElements());
        return pageOfPlayer;
    }
}



