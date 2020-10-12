package com.sikorakam.medicalcheckupsapp.api;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/testSecController")
public class ControllerForTestSecurity {

    @GetMapping("/all")
    public String allAccess(){
        return "Public, available for all users";
    }

    @GetMapping("/client")
    @PreAuthorize("hasAuthority('CLIENT') or hasAuthority('Manager') or hasAuthority('ADMIN')")
    public String clientAccess() {
        return "User Content.";
    }

    @GetMapping("/manager")
    @PreAuthorize("hasAuthority('MANAGER')")
    public String managerAccess() {
        return "Manager Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

}
