package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import project.model.entity.Pet;
import project.model.extjs.ExtData;
import project.model.service.PetService;

import java.util.List;

@Controller
@RequestMapping(value = "/pets")
public class PetsController {

    @Autowired
    private PetService petService;

    @RequestMapping(value = "/read")
    public @ResponseBody
    ExtData getPets() {
        ExtData data = new ExtData();
        try {
            List<Pet> petList = petService.getPets();
            data.add(petList);
            data.setSuccess(true);
        } catch (Exception e) {
            data.setSuccess(false);
        }
        return data;
    }

    @RequestMapping(value = "/add")
    public @ResponseBody
    ExtData addPet(@RequestParam String data) {
        ExtData extData = new ExtData();
        boolean result;
        try {
            result = petService.addPet(data);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        extData.setSuccess(result);
        return extData;
    }

    @RequestMapping(value = "/update")
    public @ResponseBody
    ExtData updatePet(@RequestParam String data) {
        ExtData extData = new ExtData();
        boolean result;
        try {
            result = petService.updatePets(data);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        extData.setSuccess(result);
        return extData;
    }

    @RequestMapping(value = "/remove")
    public @ResponseBody
    ExtData removePet(@RequestParam String data) {
        ExtData extData = new ExtData();
        boolean result;
        try {
            result = petService.removePets(data);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        extData.setSuccess(result);
        return extData;
    }
}
