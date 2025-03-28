package com.app.huffmancoding;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class CharacterCode {
    private final SimpleObjectProperty<Character> caracter;
    private final SimpleStringProperty codigo;

    public CharacterCode(Character caracter, String codigo) {
        this.caracter = new SimpleObjectProperty<>(caracter);
        this.codigo = new SimpleStringProperty(codigo);
    }

    public Character getCaracter() {
        return caracter.get();
    }

    public void setCaracter(Character caracter) {
        this.caracter.set(caracter);
    }

    public SimpleObjectProperty<Character> caracterProperty() {
        return caracter;
    }

    public String getCodigo() {
        return codigo.get();
    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public SimpleStringProperty codigoProperty() {
        return codigo;
    }
}
