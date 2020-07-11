package com.sikorakam.medicalcheckupsapp.api;

import com.sikorakam.medicalcheckupsapp.dao.entity.Client;
import com.sikorakam.medicalcheckupsapp.dao.service.ClientService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

    @Autowired
    ClientService clientService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        Client client = new Client();
        modelAndView.addObject("client", client);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public ModelAndView adminHome() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerClient(@Valid Client client, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView= new ModelAndView();
        if(bindingResult.hasErrors()){
            modelAndView.addObject("successMessage", "please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(clientService.isClientAlreadyPresent(client)){
            modelAndView.addObject("successMessage", "client already exists");
        }
        else {
            clientService.saveClient(client);
            modelAndView.addObject("successMessage", "Client registered successfully");
        }
        modelAndView.addObject("client", new Client());
        modelAndView.setViewName("register");
        return modelAndView;
    }

}
