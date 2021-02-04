package com.example.boot_employeemanagesystem.controllers;

import com.example.boot_employeemanagesystem.model.Office;
import com.example.boot_employeemanagesystem.repositories.OfficeRepository;
import com.example.boot_employeemanagesystem.services.OfficeService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class OfficeController {

    private Logger log = LoggerFactory.getLogger(OfficeController.class);
    @Autowired
    private OfficeService officeService;

    @GetMapping("/office")
    public String getOffices(Model model){
        model.addAttribute("allOffices",officeService.getAllOffices());
        return "Office/officeList";
    }

    @GetMapping("/office/add")
    public String addOffice(Model model){
        model.addAttribute("office", new Office());
        return "Office/add";
    }

    @PostMapping("/office/")
    public String saveOffice(Office office){
        officeService.save(office);
        return "redirect:/office";
    }

    @GetMapping("/office/delete/{officeId}")
    public String deleteOffice(@PathVariable("officeId") String officeId){
        officeService.deleteOffice(officeId);
        return "redirect:/office";
    }

    @GetMapping("/office/update/{officeId}")
    public String updateOffice( Model model,@PathVariable("officeId") String officeId){
        log.info("Entered office id is "+officeId);
        Office o = officeService.getOffice(officeId);
        if(o == null){
            log.info("No office found with selected officeId");
            return "Office/update";
        }else{
            log.info("Office found is "+o.toString());
            model.addAttribute("office",o);
        }
        return "Office/update";
    }
}
