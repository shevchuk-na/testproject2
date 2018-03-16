package project.model.service;

import project.model.entity.Animal;

import java.util.List;

public interface AnimalService {

    List<Animal> getAnimals();

    Animal getAnimalByName(String name);
}