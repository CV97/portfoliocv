package com.example.portfoliocv.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
/**
 * Entity-Klasse für einen World-of-Warcraft Charakter.
 * Hier nur einige wenige Informationen, um Charaktere voneinander zu unterscheiden.
 * In der Realität wären noch komplexere Informationen wie Server usw. nötig.
 */
public class WoWCharacter extends RepresentationModel<WoWCharacter> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private Set<Pet> pets = new HashSet<>();

    private String name;
    private int level;
    private String faction;
    private String race;
    private String klass;

    public WoWCharacter(String name, int level, String faction, String race, String klass) {
        this.name = name;
        this.level = level;
        this.faction = faction;
        this.race = race;
        this.klass = klass;
    }
}
