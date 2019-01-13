package com.ocppreperation.ocpguide.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by adere on 3.01.2019.
 */
@Controller
public class WelcomeController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showWelcomePage(Model model) {

        return "welcome";
    }
}
