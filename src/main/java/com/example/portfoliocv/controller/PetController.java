package com.example.portfoliocv.controller;

import com.example.portfoliocv.entity.Pet;
import com.example.portfoliocv.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {

    private final PetRepository repository;

    @GetMapping
    public Iterable<Pet> fetchAllPets() {return repository.findAll();}

    @GetMapping("/{id}")
    public Optional<Pet> fetchPetById(@PathVariable long id) {return repository.findById(id);}

    @PostMapping("/new")
    public Pet addNewPet(@RequestBody Pet pet) {return repository.save(pet);}

    @DeleteMapping("/{id}/delete")
    public void deletePetById(@PathVariable long id) {repository.deleteById(id);}

    @PutMapping("/{id}/update")
    public void updatePetById(@PathVariable long id, @RequestBody Pet petchange) {
        Optional<Pet> petstay  = repository.findById(id);

        petstay.ifPresent(pet -> updatePet(petchange, pet));
    }

    // die Familie eines Haustiers kann sich nicht ändern
    private void updatePet(Pet petchange, Pet petstay) {
        petstay.setName(petchange.getName());
        petstay.setLevel(petchange.getLevel());
        petstay.setAttacks(petchange.getAttacks());
        repository.save(petstay);
    }
}
