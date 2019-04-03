package com.ocppreperation.ocpguide.Controller;

import com.ocppreperation.ocpguide.Model.PageComponent;
import com.ocppreperation.ocpguide.Service.FileProcessor;
import com.ocppreperation.ocpguide.jpa.Chapter;
import com.ocppreperation.ocpguide.jpa.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @Autowired
    FileProcessor fileProcessor;

    @Value("${base.url}")
    public String baseUrl;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(final Model model) {



        List<Chapter> chapterList = (List<Chapter>) repository.findAll();

        model.addAttribute("chapterTitle", chapterList.get(0).getName());
        model.addAttribute(chapterList);

        model.addAttribute("baseUrl",baseUrl);

        return "welcome";
    }

    @RequestMapping(value = "/chapterOne", method = RequestMethod.GET)
    public String deneme(Model model) {

        
        return "deneme";
    }



    @RequestMapping(value = "/{urlFirst}", params = "id", method = RequestMethod.GET)
    public String getPage(final Model model, @RequestParam("id") String id) {

        model.addAttribute("baseUrl",baseUrl);

        Chapter selectedChapter = repository.findById(UUID.fromString(id));

        model.addAttribute("chapterTitle", selectedChapter.getName());

        model.addAttribute("selectedChapter", selectedChapter);

        List<Chapter> chapterList = (List<Chapter>) repository.findAll();

        model.addAttribute(chapterList);


        if(selectedChapter.getLevel() == 2) {
            model.addAttribute("selectedFirstLevelParentId", selectedChapter.getParentChapter());
        }
        if(selectedChapter.getLevel() == 3 ) {
            model.addAttribute("selectedSecondLevelParentId", selectedChapter.getParentChapter());
            model.addAttribute("selectedFirstLevelParentId", repository.findById(selectedChapter.getParentChapter()).getParentChapter());
        }

        List<PageComponent> pageComponentList = fileProcessor.readContentFromTxt(selectedChapter.getFileName());

        model.addAttribute(pageComponentList);

        if(selectedChapter.getNextChapter() != null) {
            Chapter nextChapter = repository.findById(selectedChapter.getNextChapter());
            model.addAttribute("nextPage", nextChapter);
        }

        if(selectedChapter.getPreviousChapter() != null) {
            Chapter prevChapter = repository.findById(selectedChapter.getPreviousChapter());
            model.addAttribute("prevPage", prevChapter);
        }

        return "pageTemplate";
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
