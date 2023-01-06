package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="LGAS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LGA {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String code;

    @ManyToOne
    @JoinColumn(name="state_id", nullable = false)
    private State state;

    @OneToMany
    private Set<Facility> facilities;
}
