package com.example.boot_employeemanagesystem.repositories;

import com.example.boot_employeemanagesystem.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository  extends JpaRepository<Office, String> {
}
