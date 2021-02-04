package com.example.boot_employeemanagesystem.services;

import com.example.boot_employeemanagesystem.model.Office;
import com.example.boot_employeemanagesystem.repositories.OfficeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OfficeService {

    private OfficeRepository officeRepository;

    private Logger log = LoggerFactory.getLogger(OfficeService.class);

    @Autowired
    public void setOfficeRepository(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public List<Office> getAllOffices(){
        List<Office> offices = officeRepository.findAll();
        return offices;
    }

    public void save(Office office){
        officeRepository.save(office);
    }

    public void deleteOffice(String officeId) {
        log.info("Office id is"+officeId);
        log.info("office"+officeRepository.findById(officeId));
        officeRepository.deleteById(officeId);
    }

    public Office getOffice(String officeId){
        return officeRepository.findById(officeId).orElse(null);
    }
}
