package com.banasiak.search.data;

import java.util.Map;

public class Document {

    private final Map<String, Integer> frequencyMap;
    private final String name;
    private final String body;
    private final Integer wordsCount;

    public Document(String name, String body, Map<String, Integer> frequencyMap, Integer wordsCount) {
        this.name = name;
        this.body = body;
        this.frequencyMap = frequencyMap;
        this.wordsCount = wordsCount;
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

    public String getBody() {
        return body;
    }
}
