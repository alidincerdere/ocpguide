package com.ocppreperation.ocpguide.Model;

import java.util.List;

/**
 * Created by adere on 6.04.2019.
 */
public class Answer {

    private List<Option> correctOptions;

    private String explanation;

    public List<Option> getCorrectOptions() {
        return correctOptions;
    }

    public void setCorrectOptions(List<Option> correctOptios) {
        this.correctOptions = correctOptions;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
