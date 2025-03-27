package com.app.huffmancoding;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {
    private static final int NODE_RADIUS = 20;
    private static final int VERTICAL_SPACING = 80;

    @Override
    public void start(Stage primaryStage) {
        // Crear un canvas con un tamaño fijo de 1200x800
        BorderPane root = new BorderPane();

        Label label = new Label("texto a codificar: ");
        // Top (barra con input y botón)
        TextField inputField = new TextField();
        inputField.setPromptText("ingresa un texto...");
        Button button = new Button("Generar");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Canvas canvas = new Canvas(600, 500);
                GraphicsContext gc = canvas.getGraphicsContext2D();
                StackPane container = new StackPane(canvas);
                container.setStyle(
                        "-fx-background-color: #aea19e; " +  // Fondo del contenedor
                                "-fx-border-color: #dbafa0; " +       // Color del borde
                                "-fx-border-width: 2px; " +           // Grosor del borde
                                "-fx-border-radius: 5px; "            // Bordes redondeados (opcional)
                );
                root.setCenter(container);

                Response response = new HuffmanCoding().encode(inputField.getText().toCharArray());
                drawTree(gc, response.getTree(), 250, 50, 200);
                Label bitString = new Label("cadena de bits: "+response.getBitString());
                bitString.setStyle(
                        "-fx-padding: 10 20 10 20; " +       // padding: arriba derecha abajo izquierda
                                "-fx-font-size: 16px; " +            // Tamaño de fuente
                                "-fx-font-weight: bold; "          // Negrita (opcional)
                );
                root.setBottom(bitString);
            }
        });


        HBox topBar = new HBox(10,label, inputField, button);
        root.setTop(topBar);


        // Left (panel vacío para opciones adicionales)
        Pane leftPane = new Pane();
        leftPane.setPrefWidth(200);
        root.setLeft(leftPane);


        Canvas canvas = new Canvas(600, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        StackPane container = new StackPane(canvas);
        container.setStyle(
                "-fx-background-color: #aea19e; " +  // Fondo del contenedor
                        "-fx-border-color: #dbafa0; " +       // Color del borde
                        "-fx-border-width: 2px; " +           // Grosor del borde
                        "-fx-border-radius: 5px; "            // Bordes redondeados (opcional)
        );
        root.setCenter(container);

        Response response = new HuffmanCoding().encode("algebra2".toCharArray());
        drawTree(gc, response.getTree(), 250, 50, 200);

        // Bottom (Label con mensajes)
        Label bitString = new Label("cadena de bits: "+response.getBitString());
        bitString.setStyle(
                "-fx-padding: 10 20 10 20; " +       // padding: arriba derecha abajo izquierda
                        "-fx-font-size: 16px; " +            // Tamaño de fuente
                        "-fx-font-weight: bold; "          // Negrita (opcional)
        );
        root.setBottom(bitString);

        // Configurar escena
        Scene scene = new Scene(root, 1000, 650);
        primaryStage.setTitle("árbol de Huffman");
        primaryStage.setScene(scene);
        primaryStage.show();

//        Canvas canvas = new Canvas(1200, 800);
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        // Construcción de un árbol de Huffman de ejemplo
//        Response response = new HuffmanCoding().encode("algebra2".toCharArray());
//        drawTree(gc, response.getTree(), 600, 50, 200);  // Ajustar la posición de inicio si es necesario
//        Pane rootPane = new Pane(canvas);
//        Scene scene = new Scene(rootPane, 1200, 800);  // Contenedor de 1200x800
//        primaryStage.setTitle("Árbol de Huffman");
//        primaryStage.setScene(scene);
//        primaryStage.show();


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

    public static void main(String[] args) {
        launch(args);
    }
}
