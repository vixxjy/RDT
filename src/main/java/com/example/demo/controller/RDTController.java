package com.example.demo.controller;

import com.example.demo.model.RDT;
import com.example.demo.service.RDTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class RDTController {
    @Autowired
    private RDTService rdtService;

    @PostMapping("/rdt")
    public ResponseEntity<RDT> createRDT(@RequestBody RDT rdt) {
        RDT addedRDT = rdtService.addRDT(rdt);
        return ResponseEntity.ok(addedRDT);
    }

    @GetMapping("/rdt")
    public ResponseEntity<Iterable<RDT>> allRDTs() {
        Iterable<RDT> rdts = rdtService.getAllRDT();
        return ResponseEntity.ok(rdts);
    }
}
