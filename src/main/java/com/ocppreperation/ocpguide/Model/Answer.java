package com.ocppreperation.ocpguide.Model;

import java.util.List;

/**
 * Created by adere on 6.04.2019.
 */
public class Answer {

    private List<Option> correctOptios;

    private String Explanation;

    public List<Option> getCorrectOptios() {
        return correctOptios;
    }

    public void setCorrectOptios(List<Option> correctOptios) {
        this.correctOptios = correctOptios;
    }

    public String getExplanation() {
        return Explanation;
    }

    public void setExplanation(String explanation) {
        Explanation = explanation;
    }
}
