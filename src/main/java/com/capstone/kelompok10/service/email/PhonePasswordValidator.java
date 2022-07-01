package com.capstone.kelompok10.service.email;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class PhonePasswordValidator {
    
    public Boolean phoneValidator(String phone){
        String numbers = "(.*[0-9].*)";
        if(phone.length() >= 9 && phone.length() <= 13 && phone.matches(numbers)){
            log.info("Phone number is valid");
            return true;
        }else{
            log.info("Phone numbers not valid !!");
            return false;
        }
    }

    public Boolean passwordValidator(String password){
        if(password.length() <= 8 && password.length() >= 20){
            log.info("Password must longer than 8 Character and Less than 20 Character !!");
            return false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if(!password.matches(upperCaseChars)){
            log.info("Password must have atleast one uppercase character !!");
            return false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!password.matches(lowerCaseChars)){
            log.info("Password must have atleast one lowercase character !!");
            return false;
        }
        String numbers = "(.*[0-9].*)";
        if (!password.matches(numbers)){
            log.info("Password must have atleast one number !!");
            return false;
        }else{
            return true;
        }
    }
}
