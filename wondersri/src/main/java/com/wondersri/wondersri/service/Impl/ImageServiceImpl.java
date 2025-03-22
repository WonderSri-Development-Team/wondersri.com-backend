package com.wondersri.wondersri.service.Impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.wondersri.wondersri.entity.Image;
import com.wondersri.wondersri.repo.ImageRepository;
import com.wondersri.wondersri.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    private final ImageRepository imageRepository;
    private final Cloudinary cloudinary;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, Cloudinary cloudinary) {
        this.imageRepository = imageRepository;
        this.cloudinary = cloudinary;
        logger.info("ImageServiceImpl initialized with repo: {}, cloudinary: {}", imageRepository, cloudinary);
    }

    @Override
    @Transactional
    public String uploadImage(MultipartFile image) {
        logger.info("Attempting to upload image");

        try {
            if (image == null || image.isEmpty()) {
                logger.warn("Image is null or empty");
                throw new IllegalArgumentException("Image file is required");
            }

            // Upload to Cloudinary
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String url = (String) uploadResult.get("url");
            logger.info("Image uploaded successfully to Cloudinary: {}", url);

            return url; // Return the URL only
        } catch (IOException e) {
            logger.error("Failed to upload image to Cloudinary: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to upload image: " + e.getMessage(), e);
        }
    }
}