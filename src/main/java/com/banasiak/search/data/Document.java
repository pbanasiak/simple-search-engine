package com.banasiak.search.data;

import java.util.HashMap;
import java.util.Map;

public class Document {

    private final Map<String, Integer> frequencyMap = new HashMap();
    private final  String name;
    private Integer wordsCount = 0;

    public Document(String name, String[] words) {
        this.name = name;
        for (String word : words) {
            addWordToFrequencyMap(word);
            wordsCount++;
        }
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getFrequencyMap() {
        return frequencyMap;
    }

    public Integer getWordsCount() {
        return wordsCount;
    }

    private void addWordToFrequencyMap(String word) {
        if (frequencyMap.containsKey(word)) {
            Integer actual = frequencyMap.get(word);
            frequencyMap.put(word, ++actual);
        } else {
            frequencyMap.put(word, 1);
        }
    }

}
