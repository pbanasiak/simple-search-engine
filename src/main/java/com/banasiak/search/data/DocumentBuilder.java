package com.banasiak.search.data;

import java.util.HashMap;
import java.util.Map;

public class DocumentBuilder {

    private static final String WORDS_DELIMETER = " ";

    public static Document buildDocument(String name, String body) {
        Map<String, Integer> frequencyMap = new HashMap();
        int wordsCount = 0;
        String[] words = tokenization(body);
        for (String word : words) {
            addWordToFrequencyMap(word, frequencyMap);
            wordsCount++;
        }
        return new Document(name, body, frequencyMap, wordsCount);
    }
    
    private static void addWordToFrequencyMap(String word, Map<String, Integer> frequencyMap) {
        if (frequencyMap.containsKey(word)) {
            Integer actual = frequencyMap.get(word);
            frequencyMap.put(word, ++actual);
        } else {
            frequencyMap.put(word, 1);
        }
    }

    private static String[] tokenization(String documentBody) {
        return documentBody.split(WORDS_DELIMETER);
    }
}
