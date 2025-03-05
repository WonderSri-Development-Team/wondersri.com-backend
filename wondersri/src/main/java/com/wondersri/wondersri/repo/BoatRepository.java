package com.wondersri.wondersri.repo;

import com.wondersri.wondersri.entity.Boat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoatRepository extends JpaRepository<Boat, Long> {
}