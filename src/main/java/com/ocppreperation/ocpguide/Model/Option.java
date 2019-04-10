package com.ocppreperation.ocpguide.Model;

/**
 * Created by adere on 6.04.2019.
 */
public class Option {

    private OptionLetter letter;

    private String optionText;


    public OptionLetter getLetter() {
        return letter;
    }

    public void setLetter(OptionLetter letter) {
        this.letter = letter;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }
}
