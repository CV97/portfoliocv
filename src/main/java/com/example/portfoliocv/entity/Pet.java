package com.example.portfoliocv.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("JpaAttributeTypeInspection")
@Entity
@Getter
@Setter
@NoArgsConstructor
/**
 * Entity-Klasse für ein sammelbares Haustier in World-of-Warcraft.
 */
public class Pet extends RepresentationModel<Pet> {

    public Pet(String name, int level, String family, String[] attacks) {
        this.name = name;
        this.level = level;
        this.family = family;
        this.attacks = attacks;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int level;
    String family;
    private String[] attacks;
}
