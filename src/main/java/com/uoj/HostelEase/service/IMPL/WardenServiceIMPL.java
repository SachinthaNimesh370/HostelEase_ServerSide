package com.uoj.HostelEase.service.IMPL;

import com.uoj.HostelEase.dto.WardenDTO;
import com.uoj.HostelEase.entity.AdminEntity;
import com.uoj.HostelEase.entity.VisitorEntity;
import com.uoj.HostelEase.entity.WardenEntity;
import com.uoj.HostelEase.repo.AdminRepository;
import com.uoj.HostelEase.repo.WardenRepository;
import com.uoj.HostelEase.service.WardenService;
import com.uoj.HostelEase.utill.ServiceResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WardenServiceIMPL implements WardenService {
    private final WardenRepository wardenRepository;
    private final AdminRepository adminRepository;

    public WardenServiceIMPL(WardenRepository wardenRepository, AdminRepository adminRepository) {
        this.wardenRepository = wardenRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public ServiceResponse updateWarden(WardenDTO wardenDTO) {
        Optional<WardenEntity> optionalWarden = wardenRepository.findById(wardenDTO.getWarden_id());
        System.out.println(optionalWarden.get().getWarden_id());
        if (optionalWarden.isEmpty()) {
            return new ServiceResponse(false, "Warden not found with ID", null);
        }
        if (wardenDTO.getAdmin_id() == null || wardenDTO.getAdmin_id().isEmpty()) {
            return new ServiceResponse(false, "Admin ID must not be null or empty.", null);
        }
        Optional<AdminEntity> optionalAdmin = adminRepository.findById(wardenDTO.getAdmin_id());
        if (optionalAdmin.isEmpty()) {
            return new ServiceResponse(false, "Admin not found with ID: " + wardenDTO.getAdmin_id(), null);
        }
        WardenEntity wardenEntity = optionalWarden.get();
        wardenEntity.setHostel_name(wardenDTO.getHostel_name());
        wardenEntity.setBlock(wardenDTO.getBlock());
        wardenEntity.setAdmin(optionalAdmin.get());
        wardenRepository.save(wardenEntity);
        return new ServiceResponse(true, "Warden updated successfully.", null);
    }

    @Override
    public ServiceResponse getAllWardn() {
        try {
            List<WardenEntity> wardens = wardenRepository.findAll();

            if (wardens.isEmpty()) {
                return new ServiceResponse(false, "No Any Wardens", null);
            }

            List<WardenDTO> wardenDTOs = wardens.stream().map(warden -> {
                WardenDTO dto = new WardenDTO();
                dto.setWarden_id(warden.getWarden_id());
                dto.setHostel_name(warden.getHostel_name());
                dto.setBlock(warden.getBlock());
                if (warden.getAdmin() != null) {
                    dto.setAdmin_id(warden.getAdmin().getAdmin_id());
                }
                return dto;
            }).toList();

            return new ServiceResponse(true, wardenDTOs, null);

        } catch (Exception e) {
            return new ServiceResponse(false, "Sorry. Can't Access", null);
        }
    }

    @Override
    public ServiceResponse deleteWarden(String wardenId) {
        if(isExist(wardenId)){
            try{
                wardenRepository.deleteById(wardenId);
                return new ServiceResponse(true,"Request Is Deleted",null);
            } catch (Exception e) {
                return new ServiceResponse(false,"Request Can't Delete",null);
            }
        }else {
            return new ServiceResponse(false,"Request Not Found",null);
        }
    }

    private boolean isExist(String id){
        if(wardenRepository.existsById(id)){
            return true;
        }
        return false;
    }

}
