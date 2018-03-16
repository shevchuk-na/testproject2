package project.model.service;

import project.model.entity.Animal;

import java.util.List;

public interface AnimalService {

    List<Animal> getAnimals();

    Animal getAnimalById(Long id);

    boolean addAnimal(Animal animal);

    Animal updateAnimal(Animal animal);

    boolean removeAnimalById(Long id);
}
