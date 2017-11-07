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
        DocumentTfIdfComparator documentTfIdfComparator = new DocumentTfIdfComparator(word, totalDocumentsCount,
                wordToDocumentsMap.get(word).size());
        List<Document> documents = wordToDocumentsMap.get(word);
        Collections.sort(documents, documentTfIdfComparator);
        return documents;
    }

    public double getTfIdf(String word, Document document) {
        DocumentTfIdfComparator documentTfIdfComparator = new DocumentTfIdfComparator(word, totalDocumentsCount,
                wordToDocumentsMap.get(word).size());
        return documentTfIdfComparator.getTfIdf(document);
    }

    public Map<String, List<Document>> getWordToDocumentsMap() {
        return wordToDocumentsMap;
    }


}
