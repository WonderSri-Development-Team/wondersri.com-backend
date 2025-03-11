package com.wondersri.wondersri.dto.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

public class BoatSaveRequestDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Capacity must be a positive number")
    private int capacity;

    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @MinImagesCount(message = "At least 4 images are required")
    private List<MultipartFile> images;

    // Constructors
    public BoatSaveRequestDTO() {}

    public BoatSaveRequestDTO(Long id, String name, int capacity, String description, String location, List<MultipartFile> images) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.description = description;
        this.location = location;
        this.images = images;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }
}

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinImagesCountValidator.class)
@interface MinImagesCount {
    String message() default "At least 4 images are required";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class MinImagesCountValidator implements javax.validation.ConstraintValidator<MinImagesCount, List<MultipartFile>> {
    @Override
    public boolean isValid(List<MultipartFile> images, javax.validation.ConstraintValidatorContext context) {
        return images != null && images.size() >= 4 && images.stream().allMatch(image -> image != null && !image.isEmpty());
    }
}