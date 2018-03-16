package project.model.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.entity.Animal;
import project.model.persistence.AnimalMapper;
import project.model.service.AnimalService;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalMapper animalMapper;

    @Autowired
    public AnimalServiceImpl(AnimalMapper animalMapper) {
        this.animalMapper = animalMapper;
    }

    @Override
    public List<Animal> getAnimals() {
        return animalMapper.getAnimals();
    }

    @Override
    public Animal getAnimalById(Long id) {
        return animalMapper.getAnimalById(id);
    }

    @Override
    public boolean addAnimal(Animal animal) {
        return animalMapper.addAnimal(animal);
    }

    @Override
    public Animal updateAnimal(Animal animal) {
        return animalMapper.updateAnimal(animal);
    }

    @Override
    public boolean removeAnimalById(Long id) {
        return animalMapper.removeAnimalById(id);
    }
}
