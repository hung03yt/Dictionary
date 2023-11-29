package com.example.dictionary;

public class Word {

    private String wordTarget;
    private String wordExplain;

    public Word() {
    }

    public Word(String wordTarget, String wordExplain) {
        this.wordTarget = wordTarget;
        this.wordExplain = wordExplain;
    }

    public String getWordTarget() {
        return wordTarget;
    }

    public void setWordTarget(String wordTarget) {
        this.wordTarget = wordTarget;
    }

    public String getWordExplain() {
        return wordExplain;
    }

    public void setWordExplain(String wordExplain) {
        this.wordExplain = wordExplain;
    }

    public void getWordInfo(int i) {
        System.out.format("%-8s %-1s%-31s %-1s%-40s\n", (i + 1), "|", getWordTarget(), "|", getWordExplain());
    }
}