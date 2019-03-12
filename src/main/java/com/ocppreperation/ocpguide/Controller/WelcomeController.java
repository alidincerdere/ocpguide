package com.ocppreperation.ocpguide.Controller;

import com.ocppreperation.ocpguide.jpa.Chapter;
import com.ocppreperation.ocpguide.jpa.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

/**
 * Created by adere on 3.01.2019.
 */
@Controller
public class WelcomeController {

    @Autowired
    ChapterRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(final Model model) {



        List<Chapter> chapterList = (List<Chapter>) repository.findAll();

        model.addAttribute("chapterTitle", chapterList.get(0).getName());
        model.addAttribute(chapterList);

        return "welcome";
    }

    @RequestMapping(value = "/chapterOne", method = RequestMethod.GET)
    public String deneme(Model model) {

        return "deneme";
    }



    @RequestMapping(value = "/{urlFirst}", params = "id", method = RequestMethod.GET)
    public String getPage(final Model model, @RequestParam("id") String id) {

        model.addAttribute("chapterTitle", repository.findById(UUID.fromString(id)).getName());

        List<Chapter> chapterList = (List<Chapter>) repository.findAll();

        model.addAttribute(chapterList);

        return "welcome";
    }
/*
    @RequestMapping(value = "/{urlFirst}/{urlSecond}", params = "id", method = RequestMethod.GET)
    public String getSecondPage(final Model model, @RequestParam("id") String id) {

        model.addAttribute("chapterTitle", repository.findById(UUID.fromString(id)).getName());

        List<Chapter> chapterList = (List<Chapter>) repository.findAll();

        model.addAttribute(chapterList);

        return "pageTemplate";
    }

    @RequestMapping(value = "/{urlFirst}/{urlSecond}/{urlThird}", params = "id", method = RequestMethod.GET)
    public String getThirdPage(final Model model, @RequestParam("id") String id) {

        model.addAttribute("chapterTitle", repository.findById(UUID.fromString(id)).getName());

        List<Chapter> chapterList = (List<Chapter>) repository.findAll();

        model.addAttribute(chapterList);

        return "pageTemplate";
    }
*/
}
