package com.example.boot_employeemanagesystem.controllers;

import com.example.boot_employeemanagesystem.model.Office;
import com.example.boot_employeemanagesystem.services.OfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class OfficeController {

    private Logger log = LoggerFactory.getLogger(OfficeController.class);
    @Autowired
    private OfficeService officeService;

    //Method to show the list of offices
    @GetMapping("/office")
    public String getOffices(Model model){
        model.addAttribute("allOffices",officeService.getAllOffices());
        return "Office/officeList";
    }

    //Method to add new office.
    @GetMapping("/office/add")
    public String addOffice(Model model){
        List<String> errMsg = new ArrayList<>();
        model.addAttribute("errMsg", errMsg);
        model.addAttribute("office", new Office());
        return "Office/add";
    }

    //Method to add the selected office.
    @PostMapping("/office/")
    public String saveOffice(Office office,Model model){
        List<String> errMsg = new ArrayList<>();
        if(office.getOfficeId() == null || office.getOfficeId().isEmpty()){
            errMsg.add("Office Id cannot be empty");
        }
        if(office.getArea() ==0.0){
            errMsg.add("Enter valid value for office area");
        }
        if(office.getLocation() == null || office.getLocation().isEmpty()){
            errMsg.add("Location cannot be empty");
        }
        if(!errMsg.isEmpty()){
            model.addAttribute("errMsg",errMsg);
            return "Office/add";
        }
        officeService.save(office);
        return "redirect:/office";
    }

    //Method to delete the selected office.
    @GetMapping("/office/delete/{officeId}")
    public String deleteOffice(@PathVariable("officeId") String officeId,Model model){
        Office deleteOffice = officeService.getOffice(officeId);
        List<String> errMsg = new ArrayList<String>();

        if(deleteOffice.getEmployees().isEmpty()){
            log.info("no employees assigned so deleting");
            officeService.deleteOffice(officeId);
        }else{
            log.info("Employees assigned so not deleting.!!");

            errMsg.add("Employees assigned to office. Reassign employess to delete office !!");
            model.addAttribute("errMsg", errMsg);
            return "redirect:/office/deleteError";
        }

        return "redirect:/office";
    }

    //Method called in case of error in deleting of office
    @GetMapping("/office/deleteError")
    public String deleteError(Model model){
        model.addAttribute("errMsg","Error in deleting.Employee assigned to office !!");
        model.addAttribute("allOffices",officeService.getAllOffices());
        return "Office/officeList";
    }

    //Method to update the office details
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
