package com.example.textract;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ExtractIdInfoApp extends Application {
    private List<String> extractedTextList = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select PDF ID Document To Upload");
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            // Process the selected PDF ID document
            ProcessIDDocument.processDocument(selectedFile, extractedTextList);

            // Display the extracted information
            DisplayExtractedInfo.displayInfo(extractedTextList);
        }
    }
}
