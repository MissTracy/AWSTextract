package com.example.textract;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.AmazonTextractClientBuilder;
import com.amazonaws.services.textract.model.*;

import java.io.File;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.List;


public class ProcessIDDocument {
    public static void processDocument(File documentFile, List<String> extractedTextList) {

        // Set your AWS credentials (should be retrieved securely)
        AWSCredentials credentials = new BasicAWSCredentials("hadtoremoveit", "hadtoremoveit");

        // Create an AmazonTextract client
        AmazonTextract client = AmazonTextractClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_WEST_1)
                .build();

        try {
            // Read the PDF ID document into a byte array
            byte[] documentBytes = Files.readAllBytes(documentFile.toPath());

            // Create a Document object for the document
            Document doc = new Document().withBytes(ByteBuffer.wrap(documentBytes));

            // Create an AnalyzeDocument request
            AnalyzeDocumentRequest request = new AnalyzeDocumentRequest()
                    .withDocument(doc)
                    .withFeatureTypes("TABLES", "FORMS");

            // Call the analyzeDocument method and get the result
            AnalyzeDocumentResult result = client.analyzeDocument(request);

            // Iterate over the blocks in the result and extract text
            for (Block block : result.getBlocks()) {
                if (block.getBlockType().equals("LINE")) {
                    String text = block.getText();
                    extractedTextList.add(text);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}