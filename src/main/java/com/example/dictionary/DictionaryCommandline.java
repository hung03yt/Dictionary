package com.example.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandline {

    public static List<Word> listOfWords = Dictionary.words;

    public static void showAllWords() {
        System.out.format("%-8s %-32s %-40s\n", "No", "|Vietnamese", "|English");
        for (int i = 0; i < listOfWords.size(); i++) {
            listOfWords.get(i).getWordInfo(i);
        }
    }

    public static void dictionaryBasic() {
        DictionaryManagement.insertAtStart();
        showAllWords();
    }

    public static void dictionaryAdvanced() {
        DictionaryManagement.insertAtStart();
        while(true) {
            System.out.println("\tWelcome to My Application!"
                    + "\n\t[0] Exit"
                    + "\n\t[1] Add"
                    + "\n\t[2] Remove"
                    + "\n\t[3] Update"
                    + "\n\t[4] Display"
                    + "\n\t[5] Lookup"
                    + "\n\t[6] Search"
                    + "\n\t[7] Game"
                    + "\n\t[8] Import from file"
                    + "\n\t[9] Export to file"
                    + "\n\tYour action:");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            if(s.length() == 1) {
                char c = s.charAt(0);
                int i = c - 48;
                if(i == 0) {
                    System.out.println("Exited");
                    break;
                }
                else if(i == 1) {
                    System.out.println("Add new word:");
                    DictionaryManagement.insertFromCommandline();
                }
                else if(i == 2) {
                    System.out.println("Remove word:");
                    String word = sc.nextLine();
                    DictionaryManagement.deleteWord(word);
                    sc.close();
                }
                else if(i == 3) {
                    System.out.println("Update word:");
                    String word = sc.nextLine();
                    String meaning = sc.nextLine();
                    DictionaryManagement.editDictionary(word, meaning);
                    sc.close();
                }
                else if(i == 4) {
                    showAllWords();
                }
                else if(i == 5) {
                    System.out.println("Look up word:");
                    String word = sc.nextLine();
                    System.out.println(DictionaryManagement.dictionaryLookup(word));
                    System.out.println("\n");
                }
                else if(i == 6) {
                    System.out.println("Search part of word:");
                    String word = sc.nextLine();
                    System.out.format("%-8s %-32s %-40s\n", "No", "|Vietnamese", "|English");
                    for (Word w : DictionaryCommandline.dictionarySearcher(word)){
                        w.getWordInfo(i);
                    }
                }
                else if(i == 7) {
                    int numOfQuestions = Game.getNumOfQuestions();
                    Game.loadQuestion(numOfQuestions);
                }
                else if(i == 8) {
                    DictionaryManagement.insertFromFile();
                }
                else if(i == 9) {
                    DictionaryManagement.dictionaryExportToFile();
                }
            }
            else {
                System.out.println("Action not supported");
            }
        }
    }

    public static List<Word> dictionarySearcher(String word) {
        List<Word> searchList = new ArrayList<>();
        for (int i = 0; i < listOfWords.size(); i++) {
            if ((listOfWords.get(i).getWordTarget()).toLowerCase().indexOf(word.toLowerCase()) == 0) {
                searchList.add(listOfWords.get(i));
            }
        }
        return searchList;
    }
}
