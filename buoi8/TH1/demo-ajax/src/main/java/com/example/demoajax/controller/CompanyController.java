package com.example.demoajax.controller;

import com.example.demoajax.model.Company;
import com.example.demoajax.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/demo")
public class CompanyController {
    @Autowired private CompanyService companyService;

    @GetMapping("/companyList")
    public ResponseEntity<List<Company>> getCompanyList() {
        return new ResponseEntity<List<Company>>(companyService.getCompanyList(), HttpStatus.OK);
    }

    @GetMapping("/companyList/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Integer id) {
        return new ResponseEntity<Company>(companyService.getCompanyById(id), HttpStatus.OK);
    }

    @PostMapping("/company/save")
    public ResponseEntity<Void> saveOrUpdateCompany(@RequestBody Company company) {
        companyService.saveOrUpdateCompany(company);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
