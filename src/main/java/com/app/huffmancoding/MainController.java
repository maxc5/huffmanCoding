package com.app.huffmancoding;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane bp;

//    @FXML
//    private AnchorPane ap;

    @FXML
    public void initialize() {
        loadPage("encode-view");
    }
    @FXML
    private void encode(){
        loadPage("encode-view");
    }

    @FXML
    private void decode(){
        loadPage("decode-view");
    }

    private void loadPage(String page){
        Parent root = null;
        try {
            //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main-view.fxml"));
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        bp.setCenter(root);
    }
}