package com.manufacto.ManufactureInventory.profile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.manufacto.ManufactureInventory.signuplogin.User;
import com.manufacto.ManufactureInventory.signuplogin.UserRepository;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    public ProfileDto getProfile(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ProfileDto dto = new ProfileDto();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCompanyName(user.getCompanyName());
        dto.setIndustryType(user.getIndustryType());
        dto.setAddress(user.getAddress());
        dto.setPhone(user.getPhone());
        dto.setPinCode(user.getPinCode());
        dto.setState(user.getState());
        dto.setCity(user.getCity());
        dto.setGst(user.getGst());

        return dto;
    }

    public ProfileDto updateProfile(ProfileDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setName(dto.getName());
        user.setCompanyName(dto.getCompanyName());
        user.setIndustryType(dto.getIndustryType());
        user.setAddress(dto.getAddress());
        user.setPhone(dto.getPhone());
        user.setPinCode(dto.getPinCode());
        user.setState(dto.getState());
        user.setCity(dto.getCity());
        user.setGst(dto.getGst());

        User updated = userRepository.save(user);

        ProfileDto response = new ProfileDto();

        response.setId(updated.getId());
        response.setName(updated.getName());
        response.setEmail(updated.getEmail());
        response.setCompanyName(updated.getCompanyName());
        response.setIndustryType(updated.getIndustryType());
        response.setAddress(updated.getAddress());
        response.setPhone(updated.getPhone());
        response.setPinCode(updated.getPinCode());
        response.setState(updated.getState());
        response.setCity(updated.getCity());
        response.setGst(updated.getGst());

        return response;
    }
}