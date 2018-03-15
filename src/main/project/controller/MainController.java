package main.project.controller;

import main.project.model.entity.Pet;
import main.project.model.extjs.ExtData;
import main.project.model.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/pets")
public class MainController {

    @Autowired
    private PetService petService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ExtData getPets() {

        ExtData data = new ExtData();

        List<Pet> petList = petService.getPets();

        data.add(petList);
        data.setSuccess(true);
        data.setMessage("Pet list");

        return data;
    }
}
