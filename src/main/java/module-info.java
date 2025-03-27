module com.app.huffmancoding {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;
    requires jdk.compiler;

    opens com.app.huffmancoding to javafx.fxml;
    exports com.app.huffmancoding;
}