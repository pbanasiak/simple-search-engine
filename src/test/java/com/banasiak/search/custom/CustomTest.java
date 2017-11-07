package com.banasiak.search.custom;

import com.banasiak.search.data.DataLoader;
import com.banasiak.search.data.Document;
import com.banasiak.search.data.DocumentBuilder;
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
        inputDocuments.add("Document 4: \"the lazy brown dog brown dog brown dog sat in the corner\"");
        inputDocuments.add("Document 5: \"the red fox bit customWord \"");
    }

    @Test
    public void customTestUsingDataLoader() {
        //given
        SearchEngine searchEngine = new SearchEngine();

        List<Document> documents = DataLoader.loadData(inputDocuments);

        //when
        searchEngine.addDocuments(documents);
        List<Document> search = searchEngine.search("customWord");

        System.out.println("Custom Search, Order by tf idf descending with data loader");
        for (Document document : search) {
            System.out.println(document.getName());
        }

    }

    @Test
    public void customTestUsingDocumentBuilder() {
        //given
        SearchEngine searchEngine = new SearchEngine();

        List<Document> documents = new ArrayList<Document>();
        documents.add(DocumentBuilder.buildDocument("Document 1", "the brown fox jumped over the brown customWord customWord dog"));
        documents.add(DocumentBuilder.buildDocument("Document 2", "the lazy brown dog sat customWord customWord customWord in the corner"));
        documents.add(DocumentBuilder.buildDocument("Document 3", "the red fox bit the lazy dog"));
        documents.add(DocumentBuilder.buildDocument("Document 4", "the lazy brown dog brown dog brown dog sat in the corner"));
        documents.add(DocumentBuilder.buildDocument("Document 5", "the red fox bit customWord"));

        //when
        searchEngine.addDocuments(documents);
        List<Document> search = searchEngine.search("customWord");

        System.out.println("Custom Search, Order by tf idf descending with documnetBuilder");
        for (Document document : search) {
            System.out.println(document.getName());
        }

    }

}
