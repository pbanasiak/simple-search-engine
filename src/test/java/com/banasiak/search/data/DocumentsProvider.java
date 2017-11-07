package com.banasiak.search.data;

import java.util.ArrayList;
import java.util.List;

public class DocumentsProvider {

    private static final List<String> inputDocuments;

    static {
        inputDocuments = new ArrayList();
        inputDocuments.add("Document 1: \"the brown fox jumped over the brown dog\"");
        inputDocuments.add("Document 2: \"the lazy brown dog sat in the corner\"");
        inputDocuments.add("Document 3: \"the red fox bit the lazy dog\"");
    }

    public static List<String> getInputDocuments() {
        return inputDocuments;
    }
}
