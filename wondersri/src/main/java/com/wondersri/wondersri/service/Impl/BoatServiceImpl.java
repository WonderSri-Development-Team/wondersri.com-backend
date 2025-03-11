package com.wondersri.wondersri.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.wondersri.wondersri.dto.request.BoatSaveRequestDTO;
import com.wondersri.wondersri.dto.response.BoatAllDetailResponseDto;
import com.wondersri.wondersri.entity.Boat;
import com.wondersri.wondersri.entity.Image;
import com.wondersri.wondersri.exception.ResourceNotFoundException;
import com.wondersri.wondersri.repo.BoatRepository;
import com.wondersri.wondersri.service.BoatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BoatServiceImpl implements BoatService {

    private static final Logger logger = LoggerFactory.getLogger(BoatServiceImpl.class);

    private final BoatRepository boatRepository;
    private final Cloudinary cloudinary;

    @Autowired
    public BoatServiceImpl(BoatRepository boatRepository, Cloudinary cloudinary) {
        this.boatRepository = boatRepository;
        this.cloudinary = cloudinary;
        logger.info("BoatServiceImpl initialized with repo: {}, cloudinary: {}", boatRepository, cloudinary);
    }

    @Override
    public String saveBoat(BoatSaveRequestDTO boatSaveRequestDTO) {
        logger.info("Saving boat with name: {}", boatSaveRequestDTO.getName());

        Boat boat = new Boat();
        boat.setId(boatSaveRequestDTO.getId());
        boat.setName(boatSaveRequestDTO.getName());
        boat.setCapacity(boatSaveRequestDTO.getCapacity());
        boat.setDescription(boatSaveRequestDTO.getDescription());
        boat.setLocation(boatSaveRequestDTO.getLocation());

        List<MultipartFile> images = boatSaveRequestDTO.getImages();
        if (images != null && !images.isEmpty()) {
            for (MultipartFile file : images) {
                String imageUrl = uploadImage(file);
                Image image = new Image(null, imageUrl);
                image.setBoat(boat);
                boat.addImage(image);
            }
        }

        boatRepository.save(boat);
        logger.info("Boat saved successfully with ID: {} and {} images", boat.getId(), boat.getImages().size());
        return "Boat saved successfully!";
    }

    @Override
    public List<BoatAllDetailResponseDto> getAllBoatsDetails() {
        logger.info("Fetching all boats");
        List<Boat> boats = boatRepository.findAll();

        if (boats.isEmpty()) {
            logger.warn("No boats found in the database");
            throw new ResourceNotFoundException("No boats found!");
        }

        return boats.stream()
                .map(this::mapToBoatAllDetailResponseDto)
                .collect(Collectors.toList());
    }

    private BoatAllDetailResponseDto mapToBoatAllDetailResponseDto(Boat boat) {
        List<String> imageUrls = boat.getImages().stream()
                .map(Image::getUrl)
                .collect(Collectors.toList());
        return new BoatAllDetailResponseDto(
                boat.getId(),
                boat.getName(),
                boat.getCapacity(),
                boat.getDescription(),
                boat.getLocation(),
                imageUrls
        );
    }

    private String uploadImage(MultipartFile image) {
        try {
            if (image == null || image.isEmpty()) {
                logger.warn("Image is null or empty, returning default URL");
                return "http://example.com/default-image.jpg";
            }
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String url = (String) uploadResult.get("url");
            logger.info("Image uploaded successfully: {}", url);
            return url;
        } catch (IOException e) {
            logger.error("Failed to upload image to Cloudinary: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to upload image: " + e.getMessage(), e);
        }
    }
}