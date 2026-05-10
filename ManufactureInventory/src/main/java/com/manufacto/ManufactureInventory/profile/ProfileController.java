package com.manufacto.ManufactureInventory.profile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/profile")
@CrossOrigin("*")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping("/{email}")
    public ProfileDto getProfile(@PathVariable String email) {
        return service.getProfile(email);
    }

    @PutMapping("/update")
    public ProfileDto updateProfile(@RequestBody ProfileDto dto) {
        return service.updateProfile(dto);
    }
}