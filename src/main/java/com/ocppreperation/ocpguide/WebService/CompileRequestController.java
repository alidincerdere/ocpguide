package com.ocppreperation.ocpguide.WebService;

import com.ocppreperation.ocpguide.Model.CompileInput;
import org.springframework.web.bind.annotation.*;

/**
 * Created by adere on 3.01.2019.
 */
@RestController
@RequestMapping(value = "/compileAndRun")
public class CompileRequestController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String returnResult(@RequestBody CompileInput script) {

        return script.getScript() + "hello from back end";
    }


}
