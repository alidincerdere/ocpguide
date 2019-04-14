package com.ocppreperation.ocpguide.Model;

import java.util.List;

/**
 * Created by adere on 6.04.2019.
 */
public class Question {

    private int number;

    private String text;

    private String code;

    private List<Option> options;

    private Answer correctAnswer;

    private String serializedCorrectAnswer;

    private String explanation;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
        setExplanation(correctAnswer.getExplanation());
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSerializedCorrectAnswer() {
        return serializedCorrectAnswer;
    }

    public void setSerializedCorrectAnswer(String serializedCorrectAnswer) {
        this.serializedCorrectAnswer = serializedCorrectAnswer;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
