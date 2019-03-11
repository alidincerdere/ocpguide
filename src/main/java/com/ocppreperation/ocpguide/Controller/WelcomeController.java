package com.ocppreperation.ocpguide.Controller;

import com.ocppreperation.ocpguide.jpa.Chapter;
import com.ocppreperation.ocpguide.jpa.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by adere on 3.01.2019.
 */
@Controller
public class WelcomeController {

    @Autowired
    ChapterRepository repository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(Model model) {


        List<Chapter> chapterList = (List<Chapter>) repository.findAll();

        model.addAttribute(chapterList);

        return "welcome";
    }

    @RequestMapping(value = "/chapterOne", method = RequestMethod.GET)
    public String deneme(Model model) {

        return "deneme";
    }
}
