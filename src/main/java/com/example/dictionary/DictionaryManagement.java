package com.example.dictionary;


import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {

    public static void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        int numberOfWord = Integer.parseInt(s);
        for (int i = 1; i <= numberOfWord; i++) {
            String newWord = scan.nextLine();
            String wordMean = scan.nextLine();
            Word word = new Word(newWord, wordMean);
            Dictionary.words.add(word);
        }
        scan.close();
    }

    public static void insertFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\dictionaries.txt"));
            String line = reader.readLine();
            while (line != null) {
                int indexOfTab = line.indexOf("\t");
                if (indexOfTab > 1) {
                    Word newWord = new Word(line.substring(0, indexOfTab), line.substring(indexOfTab+1));
                    Dictionary.addNewWord(newWord);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred." + e);
        }
    }

    public static void insertAtStart() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src\\engToVieCleanned.txt"));
            String line = reader.readLine();
            while (line != null) {
                int indexOfTab = line.indexOf("/");
                if (indexOfTab > 1) {
                    Word newWord = new Word(line.substring(1, indexOfTab - 1), line.substring(indexOfTab));
                    Dictionary.addNewWord(newWord);
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("An error occurred." + e);
        }
    }

    public static String dictionaryLookup(String word_) {
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (word_.toLowerCase().equals(Dictionary.words.get(i).getWordTarget().toLowerCase())) {
                return Dictionary.words.get(i).getWordExplain();
            }
        }
        return "Can't find your word!";
    }

    public static void deleteWord(String word_) {
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (word_.equals(Dictionary.words.get(i).getWordTarget())) {
                Dictionary.words.remove(Dictionary.words.get(i));
            }
            else if(i == Dictionary.words.size()-1) {
                System.out.println("Word not found");
            }
        }
    }

    public static void addNewWord(Word word) {
        Dictionary.words.add(word);
    }

    public static void editDictionary(String word, String word_) {
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (Dictionary.words.get(i).getWordTarget().equals(word)
                    && DictionaryManagement.dictionaryLookup(word) != word_) {
                Dictionary.words.get(i).setWordTarget(word_);
            }
        }
    }
    /*
    public static void editDictionary(String oldWord, Word word_) {
        for (int i = 0; i < Dictionary.words.size(); i++) {
            if (Dictionary.words.get(i).getWordTarget().equals(oldWord)) {
                Dictionary.words.get(i).setWordTarget(word_.getWordTarget());
                Dictionary.words.get(i).setWordExplain(word_.getWordExplain());
            }
        }
    }
     */
    public static void dictionaryExportToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("src\\engToVieCleanned.txt");
            for (int i = 0; i < Dictionary.words.size(); i++) {
                String line = Dictionary.words.get(i).getWordTarget() + "\t" + Dictionary.words.get(i).getWordExplain() + "\r\n";
                byte out[] = line.getBytes();
                fileOut.write(out);
            }
            fileOut.close();
        } catch (IOException e) {

            System.out.println("An error occurred." + e);
        }
    }

//    public static String getTranslate(String s) {
//        int index = s.lastIndexOf("translation");
//        int index2 = s.indexOf("\"",index);
//        int index3 = s.lastIndexOf("\"");
//        String res = s.substring(index2+4,index3);
//        return res;
//    }
}
