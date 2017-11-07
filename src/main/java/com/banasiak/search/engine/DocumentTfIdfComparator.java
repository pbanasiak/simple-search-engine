package com.banasiak.search.engine;

import com.banasiak.search.constants.Constants;
import com.banasiak.search.data.Document;

import java.util.Comparator;

public class DocumentTfIdfComparator implements Comparator<Document> {

    private final String word;

    private final Double totalDocumentsCount;
    private final Double numberOfDocumentsForWord;

    public DocumentTfIdfComparator(String word, Integer totalDocumentsCount, Integer numberOfDocumentsForWord) {
        this.word = word;
        this.totalDocumentsCount = totalDocumentsCount * 1.0;
        this.numberOfDocumentsForWord = numberOfDocumentsForWord * 1.0;
    }


    //descending
    public int compare(Document o1, Document o2) {
        Double tdIdf1 = getTfIdf(o1);
        Double tdIdf2 = getTfIdf(o2);

        if (Constants.IS_DEBUG) {
            //TODO replace with logger;
            System.out.println("Comparing " + o1.getName() + " with " + o2.getName() + ", tdIdfs : " + tdIdf1 + ", " + tdIdf2);
        }
        return tdIdf2.compareTo(tdIdf1);
    }

    /**
     * (Number of times term word appears in a document) / (Total number of words in the document)
     *
     * @param document
     * @return
     */
    private double getTf(Document document) {
        double frequency = 0.0;
        if (document.getFrequencyMap().containsKey(word)) {
            frequency = document.getFrequencyMap().get(word);
        }
        double wordsCount = document.getWordsCount();
        return frequency / wordsCount;
    }

    /**
     * log(Total number of documents / Number of documents with term word in it).
     *
     * @return
     */
    private double getIdf() {
        return Math.log10(totalDocumentsCount * 1.0 / numberOfDocumentsForWord);
    }

    public Double getTfIdf(Document document) {
        double tf = getTf(document);
        double idf = getIdf();
        Double result = tf * idf;
        if (Constants.IS_DEBUG) {
            //TODO replace with logger;
            System.out.println("TfIdf for " + word + " is equal to " + result.toString());
        }
        return result;
    }
}
