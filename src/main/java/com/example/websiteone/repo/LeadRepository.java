package com.example.websiteone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.websiteone.model.Lead;

public interface LeadRepository extends JpaRepository<Lead, Long> {

}
