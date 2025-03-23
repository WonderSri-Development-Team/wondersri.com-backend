package com.wondersri.wondersri.controller;

import com.wondersri.wondersri.dto.request.BoatSaveRequestDTO;
import com.wondersri.wondersri.dto.response.BoatAllDetailResponseDto;
import com.wondersri.wondersri.dto.response.BoatResponseDTO;
import com.wondersri.wondersri.service.BoatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "https://wondersri.com")
@RestController
@RequestMapping("/api/v1/boats")
public class BoatController {
    private static final Logger logger = LoggerFactory.getLogger(BoatController.class);

    private final BoatService boatService;

    @Autowired
    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @PostMapping("/save-boat")
    public ResponseEntity<String> saveBoat(@Valid @ModelAttribute BoatSaveRequestDTO boatSaveRequestDTO, BindingResult result) {
        logger.info("Received request to save boat: {}", boatSaveRequestDTO);

        if (result.hasErrors()) {
            String errors = result.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining(", "));
            logger.error("Validation failed: {}", errors);
            return new ResponseEntity<>("Validation failed: " + errors, HttpStatus.BAD_REQUEST);
        }

        try {
            String response = boatService.saveBoat(boatSaveRequestDTO);
            logger.info("Boat saved successfully: {}", response);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error saving boat: {}", e.getMessage(), e);
            return new ResponseEntity<>("Error saving boat: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all-boats")
    public ResponseEntity<List<BoatAllDetailResponseDto>> getAllBoats() {
        logger.info("Received request to fetch all boats");
        List<BoatAllDetailResponseDto> boats = boatService.getAllBoatsDetails();
        logger.info("Fetched {} boats", boats.size());
        return new ResponseEntity<>(boats, HttpStatus.OK);
    }
    @GetMapping("/front-page")
    public ResponseEntity<List<BoatResponseDTO>> getFrontPageBoats() {
        logger.info("Fetching 5 boats for front page");
        try {
            List<BoatResponseDTO> boats = boatService.getFrontPageBoats();
            logger.info("Successfully fetched {} boats for front page", boats.size());
            return new ResponseEntity<>(boats, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error fetching front page boats: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}