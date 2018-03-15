package main.project.model.service.impl;

import main.project.model.entity.Pet;
import main.project.model.persistence.PetMapper;
import main.project.model.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetServiceImpl implements PetService{

    private final PetMapper petMapper;

    @Autowired
    public PetServiceImpl(PetMapper petMapper) {
        this.petMapper = petMapper;
    }

    @Override
    public List<Pet> getPets() {
        return petMapper.getPets();
    }

    @Override
    public Pet getPetById(Long id) {
        return petMapper.getPetById(id);
    }

    @Override
    public boolean addPet(Pet pet) {
        return petMapper.addPet(pet);
    }

    @Override
    public Pet updatePet(Pet pet) {
        return petMapper.updatePet(pet);
    }

    @Override
    public boolean removePetById(Long id) {
        return petMapper.removePetById(id);
    }
}
