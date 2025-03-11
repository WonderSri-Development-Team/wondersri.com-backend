package com.wondersri.wondersri.repo;

import com.wondersri.wondersri.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}