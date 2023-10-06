package com.boleanuk.music.controller;

import com.boleanuk.music.model.RecordCompany;
import com.boleanuk.music.repository.RecordCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("record-companies")
public class RecordCompanyController {
    @Autowired
    RecordCompanyRepository recordCompanies;

    @GetMapping
    public List<RecordCompany> getAllRecordCompanies() {
        return this.recordCompanies.findAll();
    }

    private  RecordCompany getRecordCompany(int id) {
        return this.recordCompanies.findById(id).orElse(null);
    }

    @PostMapping
    public ResponseEntity< RecordCompany> createAlbum(@RequestBody RecordCompany recordCompany) {
        return new ResponseEntity<>(this.recordCompanies.save(recordCompany), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecordCompany> updateRecordCompany(@PathVariable int id, @RequestBody RecordCompany recordCompany) {
        RecordCompany recordCompanyToUpdate = this.getRecordCompany(id);
        if (recordCompanyToUpdate == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record Company with this id was not found.");
        }
        try {
            recordCompanyToUpdate.setName(recordCompany.getName());
            recordCompanyToUpdate.setLocation(recordCompany.getLocation());
            recordCompanyToUpdate.setEmail(recordCompany.getEmail());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Update failed. Please check fields.");
        }
        return new ResponseEntity<>(this.recordCompanies.save(recordCompanyToUpdate), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RecordCompany> deleteRecordCompany(@PathVariable int id) {
        RecordCompany recordCompanyToDelete = this.getRecordCompany(id);
        if (recordCompanyToDelete == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Record Company with this id was not found.");
        }
        this.recordCompanies.delete(recordCompanyToDelete);
        return ResponseEntity.ok(recordCompanyToDelete);
    }
}
