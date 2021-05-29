package com.example.portfoliocv.controller;

import com.example.portfoliocv.entity.Pet;
import com.example.portfoliocv.entity.WoWCharacter;
import com.example.portfoliocv.repository.WoWCharacterRepository;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/characters")
public class WoWCharacterController {

    private final WoWCharacterRepository repository;

    public WoWCharacterController(final WoWCharacterRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Iterable<WoWCharacter> fetchAllCharacters() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public WoWCharacter fetchCharacterById(@PathVariable long id) {
        Optional<WoWCharacter> result = repository.findById(id);
        WoWCharacter wowcharacter = result.get();

        Link self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WoWCharacterController.class)
                .fetchCharacterById(wowcharacter.getId())).withSelfRel();
        Link addPet = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WoWCharacterController.class)
                .fetchCharacterPets(id)).withRel("addCharacterPet").withHref("post");
        wowcharacter.add(self, addPet);

        return wowcharacter;
    }

    @PostMapping("/new")
    public WoWCharacter createNewCharacter(@RequestBody WoWCharacter add) {
        WoWCharacter wowCharacter = repository.save(add);

        Link self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WoWCharacterController.class)
                .fetchCharacterById(wowCharacter.getId())).withSelfRel();
        Link charPet = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WoWCharacterController.class)
                .fetchCharacterPets(wowCharacter.getId())).withRel("fetchCharacterPets").withHref("get");
        Link addCharacterPet = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(WoWCharacterController.class)
                .addCharacterPet(wowCharacter.getId(), null)).withRel("addCharacterPet").withHref("post");
        wowCharacter.add(self, charPet, addCharacterPet);
        return wowCharacter;
    }

    @GetMapping("/{id}/pets")
    public Set<Pet> fetchCharacterPets(@PathVariable long id) {
        Optional<WoWCharacter> wowCharacter = repository.findById(id);

        return wowCharacter.isPresent() ? wowCharacter.get().getPets() : Collections.emptySet();
    }

    @PostMapping("/{id}/pets/add")
    public WoWCharacter addCharacterPet(@PathVariable long id, @RequestBody Pet pet) {
        Optional<WoWCharacter> wowCharacter1 = repository.findById(id);
        if (!wowCharacter1.isPresent()) {
            return null;
        }

        WoWCharacter wowCharacter2 = wowCharacter1.get();
        wowCharacter2.getPets().add(pet);

        return repository.save(wowCharacter2);
    }
}
