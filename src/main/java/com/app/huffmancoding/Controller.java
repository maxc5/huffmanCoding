package com.app.huffmancoding;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Controller {
    @FXML
    private Canvas myCanvas;

    @FXML
    public void initialize() {
        GraphicsContext gc = myCanvas.getGraphicsContext2D();
        gc.fillRect(50, 50, 100, 100);
    }
}