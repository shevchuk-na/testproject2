package project.model.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.entity.Pet;
import project.model.entity.deserializers.PetDeserializer;
import project.model.persistence.PetMapper;
import project.model.service.AnimalService;
import project.model.service.PetService;

import java.io.IOException;
import java.util.List;

@Service
public class PetServiceImpl implements PetService{

    private final PetMapper petMapper;
    private final AnimalService animalService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TypeReference<List<Pet>> petTypeReference;

    @Autowired
    public PetServiceImpl(PetMapper petMapper, AnimalService animalService) {
        this.petMapper = petMapper;
        this.animalService = animalService;
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Pet.class, PetDeserializer.getInstance());
        objectMapper.registerModule(module);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        petTypeReference = new TypeReference<List<Pet>>() {
        };
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
    public boolean addPet(String data) {
        try {
            List<Pet> pets = objectMapper.readValue(data, petTypeReference);
            return petMapper.addPets(pets);
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean updatePets(String data) {
        try {
            List<Pet> pets = objectMapper.readValue(data, petTypeReference);
            return petMapper.updatePets(pets);
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public boolean removePets(String data) {
        try {
            List<Long> petIds = objectMapper.readValue(data, new TypeReference<List<Long>>() {
            });
            return petMapper.removePetsById(petIds);
        } catch (IOException e) {
            return false;
        }
    }
}
