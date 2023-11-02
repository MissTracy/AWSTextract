package com.example.textract;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisplayExtractedInfo {
    public static void displayInfo(List<String> extractedTextList) {
        // Regular expressions to extract information
        String idNumberRegex = "(ID|Identity) Number: (\\d{13})";
        String surnameRegex = "Surname: (.+)";
        String firstNamesRegex = "(First )?Names: (.+)";
        String genderRegex = "Sex: ([fm])";


        for (String text : extractedTextList) {
            Matcher idNumberMatcher = Pattern.compile(idNumberRegex).matcher(text);
            Matcher surnameMatcher = Pattern.compile(surnameRegex).matcher(text);
            Matcher firstNamesMatcher = Pattern.compile(firstNamesRegex).matcher(text);
            Matcher genderMatcher = Pattern.compile(genderRegex).matcher(text);

            if (idNumberMatcher.find()) {
                String idNumber = idNumberMatcher.group(1);
                System.out.println("ID Number: " + idNumber);
            }

            if (surnameMatcher.find()) {
                String surname = surnameMatcher.group(1);
                System.out.println("Surname: " + surname);
            }

            if (firstNamesMatcher.find()) {
                String firstNames = firstNamesMatcher.group(1);
                System.out.println("First Names: " + firstNames);
            }

            if (genderMatcher.find()) {
                String genderCode = genderMatcher.group(1);
                String gender = (genderCode.equals("f")) ? "female" : "male";
                System.out.println("Sex: " + gender);
            }
        }
    }
}
