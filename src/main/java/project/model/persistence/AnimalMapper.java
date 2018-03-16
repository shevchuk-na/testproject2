package project.model.persistence;

import project.model.entity.Animal;

import java.util.List;

public interface AnimalMapper {

    List<Animal> getAnimals();

    Animal getAnimalByName(String name);
}
