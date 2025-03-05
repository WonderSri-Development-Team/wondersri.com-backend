package com.wondersri.wondersri.service.Impl;

import com.wondersri.wondersri.dto.request.BoatSaveRequestDTO;
import com.wondersri.wondersri.dto.response.BoatAllDetailResponseDto;
import com.wondersri.wondersri.entity.Boat;
import com.wondersri.wondersri.exception.ResourceNotFoundException;
import com.wondersri.wondersri.repo.BoatRepository;
import com.wondersri.wondersri.service.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoatServiceImpl implements BoatService {

    private static final Logger logger = LoggerFactory.getLogger(BoatServiceImpl.class);

    private final BoatRepository boatRepository;

    @Autowired
    public BoatServiceImpl(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    @Override
    public String saveBoat(BoatSaveRequestDTO boatSaveRequestDTO) {
        logger.info("Saving boat with name: {}", boatSaveRequestDTO.getName());

        // Map DTO to Entity
        Boat boat = new Boat(
                boatSaveRequestDTO.getId(),
                boatSaveRequestDTO.getName(),
                boatSaveRequestDTO.getCapacity(),
                boatSaveRequestDTO.getDescription(),
                boatSaveRequestDTO.getLocation()
        );

        // Save the boat
        boatRepository.save(boat);

        logger.info("Boat saved successfully with ID: {}", boat.getId());
        return "Boat saved successfully!";
    }

    @Override
    public List<BoatAllDetailResponseDto> getAllBoatsDetails() {
        logger.info("Fetching all boats");

        // Fetch all boats from the repository
        List<Boat> boats = boatRepository.findAll();

        // Check if the list is empty
        if (boats.isEmpty()) {
            logger.warn("No boats found in the database");
            throw new ResourceNotFoundException("No boats found!");
        }

        // Map entities to DTOs
        return boats.stream()
                .map(this::mapToBoatAllDetailResponseDto)
                .collect(Collectors.toList());
    }

    // Helper method to map Boat entity to BoatAllDetailResponseDto
    private BoatAllDetailResponseDto mapToBoatAllDetailResponseDto(Boat boat) {
        return new BoatAllDetailResponseDto(
                boat.getId(),
                boat.getName(),
                boat.getCapacity(),
                boat.getDescription(),
                boat.getLocation()
        );
    }
}