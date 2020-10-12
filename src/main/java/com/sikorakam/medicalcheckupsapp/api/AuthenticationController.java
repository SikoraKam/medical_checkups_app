package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.api.requests.LoginRequest;
import com.sikorakam.medicalcheckupsapp.api.requests.SignupRequest;
import com.sikorakam.medicalcheckupsapp.api.responses.JwtResponse;
import com.sikorakam.medicalcheckupsapp.api.responses.MessageResponse;
import com.sikorakam.medicalcheckupsapp.dao.ClientRepository;
import com.sikorakam.medicalcheckupsapp.dao.RoleRepository;
import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
import com.sikorakam.medicalcheckupsapp.dao.entity.Role;
import com.sikorakam.medicalcheckupsapp.exception.NotFoundException;
import com.sikorakam.medicalcheckupsapp.security.ClientDetailsImpl;
import com.sikorakam.medicalcheckupsapp.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateClient(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        ClientDetailsImpl clientDetails = (ClientDetailsImpl) authentication.getPrincipal();
        List<String> roles = clientDetails.getAuthorities().stream()
                .map(elem -> ((GrantedAuthority) elem).getAuthority()).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt, clientDetails.getId(),
                                clientDetails.getName(),
                                clientDetails.getLastname(),
                                clientDetails.getEmail(),
                                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignupRequest signupRequest) throws NotFoundException {
        if(clientRepository.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: This email exists on our database"));
        }

        Client client = new Client(signupRequest.getName(), signupRequest.getLastname(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));

        Set<String> strRoles = signupRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role clientRole = roleRepository.findByRole("CLIENT").orElseThrow(() -> new NotFoundException("Error: role not found"));
            roles.add(clientRole);
        }else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByRole("ADMIN").orElseThrow(() -> new RuntimeException("Error: role not found"));
                        roles.add(adminRole);
                        break;
                    case "MANAGER":
                        Role managerRole = roleRepository.findByRole("MANAGER").orElseThrow(() -> new RuntimeException("Error: role not found"));
                        roles.add(managerRole);
                        break;
                    default:
                        Role clientRole = roleRepository.findByRole("CLIENT").orElseThrow(() -> new RuntimeException("Error: role not found"));
                        roles.add(clientRole);
                        break;
                }
            });
        }
        client.setRoles(roles);
        clientRepository.save(client);
        return ResponseEntity.ok(new MessageResponse("Client registered successfully"));
    }



//    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
//    public ModelAndView login(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("login");
//        return modelAndView;
//    }

//    @RequestMapping(value = "/register", method = RequestMethod.GET)
//    public ModelAndView register(){
//        ModelAndView modelAndView = new ModelAndView();
//        Client client = new Client();
//        modelAndView.addObject("client", client);
//        modelAndView.setViewName("register");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/home", method = RequestMethod.GET)
//    public ModelAndView home(){
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public ModelAndView adminHome() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("admin");
//        return modelAndView;
//    }
//
//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ModelAndView registerClient(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult, ModelMap modelMap) {
//        ModelAndView modelAndView= new ModelAndView();
//        if(bindingResult.hasErrors()){
//            modelAndView.addObject("successMessage", "please correct the errors in form!");
//            modelMap.addAttribute("bindingResult", bindingResult);
//        }
//        else if(clientService.isClientAlreadyPresent(client)){
//            modelAndView.addObject("successMessage", "client already exists");
//        }
//        else {
//            clientService.saveClient(client);
//            modelAndView.addObject("successMessage", "Client registered successfully");
//        }
//        modelAndView.addObject("client", new Client());
//        modelAndView.setViewName("register");
//        return modelAndView;
//    }

}
