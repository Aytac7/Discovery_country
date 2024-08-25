package com.example.discovery_country.controller.auth;



import com.example.discovery_country.model.dto.request.auth.ChangePassword;
import com.example.discovery_country.service.auth.ForgotPasswordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forgotPassword")

public class ForgotPasswordController {


    private final ForgotPasswordService forgotPasswordService;


    public ForgotPasswordController(ForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService=forgotPasswordService;
    }


    @PostMapping("/verifyMail/{email}")
    public ResponseEntity<String> verifyEmail(@PathVariable String email) {
        return  forgotPasswordService.verifyEmail(email);


    }
    @PostMapping("/verifyOtp/{otp}/{email}")
    public ResponseEntity<String> verifyOtp(@PathVariable Integer otp, @PathVariable String email) {
            return forgotPasswordService.verifyOtp(otp, email);

    }

    @PostMapping("/changePassword/{email}")
    public ResponseEntity<String> changePasswordHandler(@RequestBody ChangePassword changePassword,
                                                        @PathVariable String email) {
       return forgotPasswordService.changePasswordHandler(changePassword,email);
    }

}