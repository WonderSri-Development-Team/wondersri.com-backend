package com.wondersri.wondersri.service.Impl;

import com.wondersri.wondersri.dto.request.BoatSaveRequestDTO;
import com.wondersri.wondersri.entity.Boat;
import com.wondersri.wondersri.repo.BoatRepository;
import com.wondersri.wondersri.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoatServiceImpl implements BoatService {

    @Autowired
    private BoatRepository boatRepository;

    @Override
    public String saveBoat(BoatSaveRequestDTO boatSaveRequestDTO) {
        Boat boat = new Boat(boatSaveRequestDTO.getId(),
                boatSaveRequestDTO.getName(),
                boatSaveRequestDTO.getCapacity(),
                boatSaveRequestDTO.getDescription(),
                boatSaveRequestDTO.getLocation());
        boatRepository.save(boat);
        return "Saved";
    }
}
