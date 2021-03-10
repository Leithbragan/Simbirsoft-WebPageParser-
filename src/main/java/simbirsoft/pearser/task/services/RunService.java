package simbirsoft.pearser.task.services;

import java.io.IOException;
import java.util.HashMap;


public class RunService {

    public void run(String url) throws IOException{
        PrinterService printer = new PrinterService();
        FileService fileService = new FileService(url);
        fileService.getHTMLPage();
        HashMap<String, Integer> wordsData = fileService.collectDataFromHTML();
        printer.printCountWords(printer.formatStringCount(wordsData));
        printer.printWords(wordsData);
        fileService.putDataToFile(wordsData);

    }
}
