package com.banasiak.search.engine;

import com.banasiak.search.data.DataLoader;
import com.banasiak.search.data.Document;
import com.banasiak.search.data.DocumentsProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchEngineTest {

    @Test
    public void shouldAddDocument() {
        //given
        SearchEngine searchEngine = new SearchEngine();
        Iterator<String> iterator = DocumentsProvider.getInputDocuments().iterator();
        String inputDocument1 = iterator.next();
        String inputDocument2 = iterator.next();
        Document document1 = DataLoader.createDocument(inputDocument1);
        Document document2 = DataLoader.createDocument(inputDocument2);

        //when
        searchEngine.addDocument(document1);
        searchEngine.addDocument(document2);

        //then
        assertThat(searchEngine.getWordToDocumentsMap().keySet().size()).isEqualTo(10);
        assertThat(searchEngine.getWordToDocumentsMap().get("the").size()).isEqualTo(2);
        assertThat(searchEngine.getWordToDocumentsMap().get("sat").size()).isEqualTo(1);
    }

    @Test
    public void shouldSearchAndSortByThe() {
        //given
        SearchEngine searchEngine = new SearchEngine();
        List<String> inputDocuments = DocumentsProvider.getInputDocuments();
        List<Document> documents = DataLoader.loadData(inputDocuments);

        //when
        searchEngine.addDocuments(documents);
        List<Document> searchByThe = searchEngine.search("the");
        //then
        assertThat(searchByThe.size()).isEqualTo(3);
    }

    @Test
    public void shouldSearchAndSortByBrown() {
        //given
        SearchEngine searchEngine = new SearchEngine();
        List<String> inputDocuments = DocumentsProvider.getInputDocuments();
        List<Document> documents = DataLoader.loadData(inputDocuments);

        //when
        searchEngine.addDocuments(documents);
        List<Document> searchByBrown = searchEngine.search("brown");
        //then
        assertThat(searchByBrown.size()).isEqualTo(2);
        assertThat(searchByBrown.get(0).getName()).isEqualTo("Document 1");
        assertThat(searchByBrown.get(1).getName()).isEqualTo("Document 2");
    }

    @Test
    public void shouldSearchAndSortByFox() {
        //given
        SearchEngine searchEngine = new SearchEngine();
        List<String> inputDocuments = DocumentsProvider.getInputDocuments();
        List<Document> documents = DataLoader.loadData(inputDocuments);

        //when
        searchEngine.addDocuments(documents);
        List<Document> searchByBrown = searchEngine.search("fox");
        //then
        assertThat(searchByBrown.size()).isEqualTo(2);
        assertThat(searchByBrown.get(0).getName()).isEqualTo("Document 3");
        assertThat(searchByBrown.get(1).getName()).isEqualTo("Document 1");
    }

    @Test
    public void shouldSearchAndSortByLazy() {
        //given
        SearchEngine searchEngine = new SearchEngine();
        List<String> inputDocuments = DocumentsProvider.getInputDocuments();
        List<Document> documents = DataLoader.loadData(inputDocuments);

        //when
        searchEngine.addDocuments(documents);
        List<Document> searchByLazy = searchEngine.search("lazy");
        assertThat(searchByLazy.size()).isEqualTo(2);

        double tfidf1 = searchEngine.getTfIdf("lazy", documents.get(0)); //Document 1
        double tfidf2 = searchEngine.getTfIdf("lazy", documents.get(1)); //Document 2
        double tfidf3 = searchEngine.getTfIdf("lazy", documents.get(2)); //Document 3

        assertThat(tfidf1).isEqualTo(0);
        assertThat(tfidf2).isEqualTo(0.022011407381960155);
        assertThat(tfidf3).isEqualTo(0.025155894150811604);
        assertThat(searchByLazy.get(0).getName()).isEqualTo("Document 3");
        assertThat(searchByLazy.get(1).getName()).isEqualTo("Document 2");

    }

    @Test
    public void shouldSearchWikipediaExample() {
        //given
        SearchEngine searchEngine = new SearchEngine();
        List<String> inputDocuments = new ArrayList();
        inputDocuments.add("Document1 : \"this is a a sample\"");
        inputDocuments.add("Document2: \"this is another another example example example\"");

        List<Document> documents = DataLoader.loadData(inputDocuments);

        //when
        searchEngine.addDocuments(documents);
        List<Document> result1 = searchEngine.search("this");
        List<Document> result2 = searchEngine.search("example");

        //then
        assertThat(result1.size()).isEqualTo(2);
        assertThat(result2.size()).isEqualTo(1);
    }

    @Test
    public void shouldGetTfIdfFromWikipediaExampleAndSortByThis() {
        //given
        SearchEngine searchEngine = new SearchEngine();
        List<String> inputDocuments = new ArrayList();
        inputDocuments.add("Document1: \"this is a a sample\"");
        inputDocuments.add("Document2: \"this is another another example example example\"");

        List<Document> documents = DataLoader.loadData(inputDocuments);
        //when
        searchEngine.addDocuments(documents);
        double tfidf3 = searchEngine.getTfIdf("this", documents.get(0)); //Documment1
        double tfIdf4 = searchEngine.getTfIdf("this", documents.get(1)); //Document2

        assertThat(tfidf3).isEqualTo(0);
        assertThat(tfIdf4).isEqualTo(0);
    }

    @Test
    public void shouldGetTfIdfFromWikipediaExampleAndSortByExample() {
        //given
        SearchEngine searchEngine = new SearchEngine();
        List<String> inputDocuments = new ArrayList();
        inputDocuments.add("Document1: \"this is a a sample\"");
        inputDocuments.add("Document2: \"this is another another example example example\"");

        List<Document> documents = DataLoader.loadData(inputDocuments);

        //when
        searchEngine.addDocuments(documents);
        double tfidf1 = searchEngine.getTfIdf("example", documents.get(0)); //Documment1
        double tfidf2 = searchEngine.getTfIdf("example", documents.get(1)); //Document2
        List<Document> searchByExample = searchEngine.search("example");
        //then
        assertThat(tfidf1).isEqualTo(0);
        assertThat(tfidf2).isEqualTo(0.12901285528456335);
        assertThat(searchByExample.size()).isEqualTo(1);
        assertThat(searchByExample.get(0).getName()).isEqualTo("Document2");
    }

}
