package main.project.model.entity;

import java.io.Serializable;

public class Pet implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private Animal animal;
    private float weight;
    private boolean adopted;

    public Pet() {
    }

    public Pet(Long id, String name, Animal animal, float weight, boolean adopted) {
        this.id = id;
        this.name = name;
        this.animal = animal;
        this.weight = weight;
        this.adopted = adopted;
    }

    public Pet(String name, float weight, boolean adopted) {
        this.name = name;
        this.weight = weight;
        this.adopted = adopted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }
}
