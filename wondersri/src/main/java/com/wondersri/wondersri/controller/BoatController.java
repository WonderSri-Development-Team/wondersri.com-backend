package com.wondersri.wondersri.controller;

import com.wondersri.wondersri.dto.request.BoatSaveRequestDTO;
import com.wondersri.wondersri.dto.response.BoatAllDetailResponseDto;
import com.wondersri.wondersri.service.BoatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/boats")
public class BoatController {

    private static final Logger logger = LoggerFactory.getLogger(BoatController.class);

    private final BoatService boatService;

    @Autowired
    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @PostMapping
    public ResponseEntity<String> saveBoat(@Valid @RequestBody BoatSaveRequestDTO boatSaveRequestDTO) {
        logger.info("Received request to save boat: {}", boatSaveRequestDTO);

        String response = boatService.saveBoat(boatSaveRequestDTO);
        logger.info("Boat saved successfully: {}", response);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoatAllDetailResponseDto>> getAllBoats() {
        logger.info("Received request to fetch all boats");

        List<BoatAllDetailResponseDto> boats = boatService.getAllBoatsDetails();
        logger.info("Fetched {} boats", boats.size());

        return new ResponseEntity<>(boats, HttpStatus.OK);
    }
}