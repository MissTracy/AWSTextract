module com.example.textract {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires aws.java.sdk.core;
    requires aws.java.sdk.textract;

    opens com.example.textract to javafx.fxml;
    exports com.example.textract;
}