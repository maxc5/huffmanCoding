package com.app.huffmancoding;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class CharacterCode {
    private final char character;
    private final String code;

    public CharacterCode(char character, String code) {
        this.character = character;
        this.code = code;
    }

    public char getCharacter() {
        return character;
    }

    public String getCode() {
        return code;
    }
}
