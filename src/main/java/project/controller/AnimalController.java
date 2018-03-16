package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.model.entity.Animal;
import project.model.extjs.ExtData;
import project.model.service.AnimalService;

import java.util.List;

@Controller
@RequestMapping(value = "/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @RequestMapping(value = "/read")
    public @ResponseBody
    ExtData getPets() {
        ExtData data = new ExtData();
        try {
            List<Animal> animalList = animalService.getAnimals();
            data.add(animalList);
            data.setSuccess(true);
        } catch (Exception e) {
            data.setSuccess(false);
        }
        return data;
    }
}
