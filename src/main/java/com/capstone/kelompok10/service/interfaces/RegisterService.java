package com.capstone.kelompok10.service.interfaces;

import com.capstone.kelompok10.model.payload.RegistrationRequest;

public interface RegisterService {
    String register(RegistrationRequest request);
    boolean confirmToken(String token);
    String buildEmail(String name, String link);
    // void sendVerificationEmail(UserEntity userEntity, String siteURL);
}
