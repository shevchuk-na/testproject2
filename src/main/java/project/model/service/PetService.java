package project.model.service;

import project.model.entity.Pet;

import java.util.List;

public interface PetService {

   List<Pet> getPets();

   Pet getPetById(Long id);

   boolean addPet(String data);

   boolean updatePets(String data);

   boolean removePets(String data);

}
