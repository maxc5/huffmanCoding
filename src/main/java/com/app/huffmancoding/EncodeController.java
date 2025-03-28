package com.app.huffmancoding;

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
    private TableView<CharacterCode> tableView; // El TableView definido en el FXML
    @FXML
    private TableColumn<CharacterCode, Character> characterColumn; // Columna "Caracter"
    @FXML
    private TableColumn<CharacterCode, String> codeColumn; // Columna "Codigo"


    @FXML
    public void initialize() {
//        GraphicsContext gc = myCanvas.getGraphicsContext2D();
//        gc.fillRect(50, 50, 100, 100);
//        myCanvas.widthProperty().bind(canvasContainer.widthProperty());
//        myCanvas.heightProperty().bind(canvasContainer.heightProperty());
        //tableView.getColumns().addAll(caracterColumn, codigoColumn);
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
        loadDictionary(response.getDictionary());
    }

    private void loadDictionary(Map<Character, String> map){
        tableView.getItems().clear();
        // Crear las columnas
        characterColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().caracterProperty();
        });
        codeColumn.setCellValueFactory(cellData -> {
            return cellData.getValue().codigoProperty();
        });
        // Agregar las columnas al TableView
        map.forEach((key, value) -> tableView.getItems().add(new CharacterCode(key, value)));

    }
}
