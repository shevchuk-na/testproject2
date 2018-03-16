package project.model.entity.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import project.model.entity.Animal;
import project.model.entity.Pet;

import java.io.IOException;

public class PetDeserializer extends StdDeserializer<Pet> {

    private static PetDeserializer instance = new PetDeserializer();

    private PetDeserializer() {
        this(null);
    }

    private PetDeserializer(Class<?> vc) {
        super(vc);
    }

    public static PetDeserializer getInstance() {
        return instance;
    }

    @Override
    public Pet deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {

        JsonNode petNode = jp.getCodec().readTree(jp);
        Pet pet = new Pet();
        Animal animal = new Animal();
        if (petNode.get("id") != null) {
            pet.setId(petNode.get("id").longValue());
        }
        pet.setName(petNode.get("name").textValue());
        pet.setWeight(Float.parseFloat(petNode.get("weight").asText()));
        pet.setAdopted(petNode.get("adopted").asBoolean());
        animal.setId(petNode.get("animal.id").asLong());
        animal.setName(petNode.get("animal.name").textValue());
        animal.setDescription(petNode.get("animal.description").textValue());
        pet.setAnimal(animal);
        return pet;
    }
}