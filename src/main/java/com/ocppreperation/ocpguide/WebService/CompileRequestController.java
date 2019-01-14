package com.ocppreperation.ocpguide.WebService;

import com.ocppreperation.ocpguide.Model.CompileInput;
import com.ocppreperation.ocpguide.Model.CompileOutput;
import com.ocppreperation.ocpguide.Service.CompilerRestClient;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

/**
 * Created by adere on 3.01.2019.
 */
@RestController
@RequestMapping(value = "/compileAndRun")
public class CompileRequestController {

    @Autowired
    CompilerRestClient compilerRestClient;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String returnResult(@RequestBody CompileInput script) {

        Response response = compilerRestClient.sendCompileRequest(script);

        CompileOutput compileOutput = response.readEntity(CompileOutput.class);

        if(compileOutput.getStatusCode() == HttpStatus.OK.value()) {

            return compileOutput.getOutput();
        }

        return "Consult Administrator" + compileOutput.getStatusCode();
    }


}
