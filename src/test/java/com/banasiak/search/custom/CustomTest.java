package com.banasiak.search.custom;

import com.banasiak.search.data.DataLoader;
import com.banasiak.search.data.Document;
import com.banasiak.search.engine.SearchEngine;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomTest {

    private static final List<String> inputDocuments;

    static {
        inputDocuments = new ArrayList();
        inputDocuments.add("Document 1: \"the brown fox jumped over the brown customWord customWord dog\"");
        inputDocuments.add("Document 2: \"the lazy brown dog sat customWord customWord customWord in the corner\"");
        inputDocuments.add("Document 3: \"the red fox bit the lazy dog\"");
        inputDocuments.add("Document 5: \"the lazy brown dog brown dog brown dog sat in the corner\"");
        inputDocuments.add("Document 6: \"the red fox bit customWord \"");
    }

    @Test
    public void customTest() {
        //given
        SearchEngine searchEngine = new SearchEngine();

        List<Document> documents = DataLoader.loadData(inputDocuments);

        //when
        searchEngine.addDocuments(documents);
        List<Document> search = searchEngine.search("customWord");

        System.out.println("Custom Search, Order by tf idf descending");
        for (Document document : search) {
            System.out.println(document.getName());
        }

    }

}
