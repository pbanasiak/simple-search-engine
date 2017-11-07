package com.banasiak.search.engine;

import com.banasiak.search.data.Document;

import java.util.*;

public class SearchEngine {

    private final Map<String, List<Document>> wordToDocumentsMap = new HashMap();

    private Integer totalDocumentsCount = 0;

    public void addDocument(Document document) {
        totalDocumentsCount++;
        for (String word : document.getFrequencyMap().keySet()) {
            if (!wordToDocumentsMap.containsKey(word)) {
                List<Document> list = new ArrayList();
                list.add(document);
                wordToDocumentsMap.put(word, list);
            } else {
                wordToDocumentsMap.get(word).add(document);
            }
        }
    }

    public void addDocuments(List<Document> documents) {
        for (Document document : documents) {
            addDocument(document);
        }
    }

    public List<Document> search(String word) {
        DocumentTfIdfComparator documentTfIdfComparator = getComparator(word);
        List<Document> documents = wordToDocumentsMap.get(word);
        if (documents != null && !documents.isEmpty()) {
            Collections.sort(documents, documentTfIdfComparator);
            return documents;
        } else {
            return new ArrayList();
        }
    }

    public double getTfIdf(String word, Document document) {
        DocumentTfIdfComparator documentTfIdfComparator = getComparator(word);
        return documentTfIdfComparator.getTfIdf(document);
    }

    private DocumentTfIdfComparator getComparator(String word) {
        Integer numberOfDocumentsForWord = 0;
        if (wordToDocumentsMap.containsKey(word)) {
            numberOfDocumentsForWord = wordToDocumentsMap.get(word).size();
        }
        return new DocumentTfIdfComparator(word, totalDocumentsCount,
                numberOfDocumentsForWord);
    }

    public Map<String, List<Document>> getWordToDocumentsMap() {
        return wordToDocumentsMap;
    }


}
