package project.model.persistence;

import project.model.entity.Pet;

import java.util.List;

public interface PetMapper {

    List<Pet> getPets();

    Pet getPetById(Long id);

    boolean addPets(List<Pet> pets);

    boolean updatePets(List<Pet> pets);

    boolean removePetsById(List<Long> petIds);
}
