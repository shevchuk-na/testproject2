package main.project.model.persistence;

import main.project.model.entity.Pet;

import java.util.List;

public interface PetMapper {

    List<Pet> getPets();

    Pet getPetById(Long id);

    boolean addPet(Pet pet);

    Pet updatePet(Pet pet);

    boolean removePetById(Long id);
}
