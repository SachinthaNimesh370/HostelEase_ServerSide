package com.uoj.HostelEase.controller;

import com.uoj.HostelEase.dto.RoomDTO;
import com.uoj.HostelEase.service.WardenService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/warden")
public class WardenController {
    private final WardenService wardenService;

    public WardenController(WardenService wardenService) {
        this.wardenService = wardenService;
    }

    //New Room Save
    @PostMapping("/newroom")
    public String newRoom(@RequestBody RoomDTO roomDTO) {
        wardenService.newRoom(roomDTO);
        return null;
    }

}
