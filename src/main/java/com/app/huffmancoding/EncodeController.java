package com.app.huffmancoding;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;


import java.util.List;
import java.util.Map;

public class EncodeController {

    private static final int NODE_RADIUS = 20;
    private static final int VERTICAL_SPACING = 80;

    @FXML
    private Canvas myCanvas;
    @FXML
    private TextField input;
    @FXML
    private Label bitStringLabel;
    @FXML
    private TableView<CharacterCode> tableView;
    @FXML
    private TableColumn<CharacterCode, Character> characterColumn;
    @FXML
    private TableColumn<CharacterCode, String> codeColumn;


    @FXML
    public void initialize() {
        characterColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CharacterCode, Character>, ObservableValue<Character>>() {
            @Override
            public ObservableValue<Character> call(TableColumn.CellDataFeatures<CharacterCode, Character> cellData) {
                return new ReadOnlyObjectWrapper<Character>(cellData.getValue().getCharacter());
            }
        });
        codeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CharacterCode, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<CharacterCode, String> cellData) {
                return new ReadOnlyStringWrapper(cellData.getValue().getCode());
            }
        });

//        priceColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Movie, Double>, ObservableValue<Double>>() {
//            @Override
//            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Movie, Double> param) {
//                return param.getValue().priceProperty();
//            }
//        });
    }

    private void drawTree(GraphicsContext gc, Node node, double x, double y, double horizontalOffset) {
        if (node == null) {
            return;
        }
        gc.setFill(Color.LIGHTBLUE);
        gc.fillOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(x - NODE_RADIUS, y - NODE_RADIUS, NODE_RADIUS * 2, NODE_RADIUS * 2);

        String text = node.isLeafNode() ? String.valueOf(node.getCharacter() + "|" + node.getFrequency()) : String.valueOf(node.getFrequency());
        gc.setFill(Color.BLACK);
        gc.setFont(new Font(14));
        gc.fillText(text, x - 10, y + 5);

        if (node.getLeft() != null) {
            double childX = x - horizontalOffset * 0.7;
            double childY = y + VERTICAL_SPACING;
            gc.strokeLine(x, y + NODE_RADIUS, childX, childY - NODE_RADIUS);
            gc.fillText("0", (x + childX) / 2, (y + childY - 10) / 2); // Etiqueta para la arista izquierda
            drawTree(gc, node.getLeft(), childX, childY, horizontalOffset / 2);
        }

        if (node.getRight() != null) {
            double childX = x + horizontalOffset * 0.7;
            double childY = y + VERTICAL_SPACING;
            gc.strokeLine(x, y + NODE_RADIUS, childX, childY - NODE_RADIUS);
            gc.fillText("1", (x + childX) / 2, (y + childY - 10) / 2); // Etiqueta para la arista derecha
            drawTree(gc, node.getRight(), childX, childY, horizontalOffset / 2);
        }
    }

    public void encode(MouseEvent mouseEvent) {
        GraphicsContext gc = myCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        Response response = new HuffmanCoding().encode(input.getText().toCharArray());
        drawTree(gc, response.getTree(), 250, 50, 200);
        bitStringLabel.setText("cadena de bits: "+ response.getBitString());
        // Limpiar la tabla
        tableView.getItems().clear();
        // Agregar todos los elementos de la lista a la tabla
        tableView.getItems().addAll(response.getDictionary());
    }
}
