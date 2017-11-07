package com.banasiak.search.data;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DataLoaderTest {


    @Test
    public void shouldVerifyPattern() {
        //given
        List<String> inputDocuments = DocumentsProvider.getInputDocuments();

        //when
        boolean result = true;
        for (String inputDocument : inputDocuments) {
            if (!DataLoader.verifyPattern(inputDocument)) {
                result = false;
            }
        }

        //then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldFailPattern() {
        //given
        List<String> inputDocuments = new ArrayList();
        inputDocuments.addAll(DocumentsProvider.getInputDocuments());

        //when
        inputDocuments.add("Document1 ssss");
        boolean result = true;
        for (String inputDocument : inputDocuments) {
            if (!DataLoader.verifyPattern(inputDocument)) {
                result = false;
            }
        }

        //then
        assertThat(result).isFalse();
    }

    @Test
    public void shouldLoadData() {
        //given
        List<String> inputDocuments = new ArrayList();
        inputDocuments.addAll(DocumentsProvider.getInputDocuments());

        //when
        inputDocuments.add("Document1 ssss");
        List<Document> documents = DataLoader.loadData(inputDocuments);

        //then
        assertThat(documents.size()).isEqualTo(inputDocuments.size() - 1);
    }

    @Test
    public void shouldCreateDocument() {
        //given
        String inputDocument = DocumentsProvider.getInputDocuments().iterator().next();

        //when
        Document document = DataLoader.createDocument(inputDocument);

        //then
        assertThat(document.getName()).isEqualTo("Document 1");
        assertThat(document.getBody()).isEqualTo("the brown fox jumped over the brown dog");
        assertThat(document.getFrequencyMap().keySet().size()).isEqualTo(6);
        assertThat(document.getFrequencyMap().get("the")).isEqualTo(2);
        assertThat(document.getFrequencyMap().get("dog")).isEqualTo(1);
    }
}
