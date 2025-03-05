package com.wondersri.wondersri.controller;

import com.wondersri.wondersri.dto.request.BoatSaveRequestDTO;
import com.wondersri.wondersri.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/boat")
public class BoatController {

    @Autowired
    private BoatService boatService;
    @PostMapping(path = "/save-Boat")
    public String saveBoat(@RequestBody BoatSaveRequestDTO boatSaveRequestDTO) {
        return boatService.saveBoat(boatSaveRequestDTO);
    }
}
