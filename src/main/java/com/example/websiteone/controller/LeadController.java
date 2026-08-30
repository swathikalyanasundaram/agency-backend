package com.example.websiteone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.websiteone.model.Lead;
import com.example.websiteone.repo.LeadRepository;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    @Autowired
    private LeadRepository repo;

    @PostMapping
    public Lead createLead(@RequestBody Lead lead) {
        return repo.save(lead);
    }

    @GetMapping
    public List<Lead> getAllLeads() {
        return repo.findAll();
    }
}
