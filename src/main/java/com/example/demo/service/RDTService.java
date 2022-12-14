package com.example.demo.service;

import com.example.demo.model.RDT;
import com.example.demo.repository.RDTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RDTService {
    @Autowired
    private RDTRepository rdtRepository;
    public RDT addRDT(RDT rdt) {
        return rdtRepository.save(rdt);
    }

    public Iterable<RDT> getAllRDT() {
        return rdtRepository.findAll();
    }
}
