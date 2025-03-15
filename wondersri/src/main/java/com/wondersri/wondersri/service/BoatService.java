package com.wondersri.wondersri.service;

import com.wondersri.wondersri.dto.request.BoatSaveRequestDTO;
import com.wondersri.wondersri.dto.response.BoatAllDetailResponseDto;
import com.wondersri.wondersri.dto.response.BoatResponseDTO;
import com.wondersri.wondersri.entity.Boat;

import java.util.List;

public interface BoatService {
    String saveBoat(BoatSaveRequestDTO boatSaveRequestDTO);

    List<BoatAllDetailResponseDto> getAllBoatsDetails();

    List<BoatResponseDTO> getFrontPageBoats();

    Object convertToDTO(Boat boat);
}
