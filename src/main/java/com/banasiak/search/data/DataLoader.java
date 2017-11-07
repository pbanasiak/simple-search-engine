package com.banasiak.search.data;

import com.banasiak.search.constants.Constants;

import java.util.ArrayList;
import java.util.List;

public class DataLoader {

    private static final String DOCUMENT_INPUT_PATTERN = "^[a-zA-Z0-9 ]+: \"[a-zA-Z0-9 ]+\"$";
    private static final String INPUT_DELIMETER = ": ";

    public static boolean verifyPattern(String input) {
        Boolean result = input.matches(DOCUMENT_INPUT_PATTERN);
        if (!result) {
            if (Constants.IS_DEBUG) {
                //TODO replace with logger;
                System.out.println("Pattern verification failed for " + input);
            }
        }
        return result;
    }

    public static List<Document> loadData(List<String> inputDocuments) {
        List<Document> documents = new ArrayList();
        for (String inputDocument : inputDocuments) {
            if (verifyPattern(inputDocument)) {
                Document document = createDocument(inputDocument);
                documents.add(document);
            }
        }
        return documents;
    }

    public static Document createDocument(String inputDocument) {
        String[] strings = inputDocument.split(INPUT_DELIMETER);
        String name = strings[0];
        //remove quotes
        String documentBody = strings[1].replace("\"", "");
        return DocumentBuilder.buildDocument(name, documentBody);
    }

}
