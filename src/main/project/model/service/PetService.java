package main.project.model.service;

import main.project.model.entity.Pet;

import java.util.List;

public interface PetService {

   List<Pet> getPets();

   Pet getPetById(Long id);

   boolean addPet(Pet pet);

   Pet updatePet(Pet pet);

   boolean removePetById(Long id);

}
