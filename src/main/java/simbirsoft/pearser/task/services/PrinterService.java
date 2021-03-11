package simbirsoft.pearser.task.services;

import java.util.HashMap;

public class PrinterService {

    public void printWords(HashMap<String, Integer> wordsData) {
        for (String word : wordsData.keySet()) {
            System.out.println(word + ": " + wordsData.get(word));
        }
    }

    public String formatStringCount(HashMap<String, Integer> wordsData) {
        return "---------------------------------------\n" + "Количество уникальных слов: " + wordsData.size()
                + "\n---------------------------------------\n";
    }

    public void printCountWords(String wordsCount) {
        System.out.println(wordsCount);

    }
}
