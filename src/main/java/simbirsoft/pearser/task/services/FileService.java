package simbirsoft.pearser.task.services;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.StringTokenizer;


public class FileService {

    private static final int ARRAY_SIZE = 4096;
    private final static Logger logger = Logger.getLogger(ParsingService.class);
    private final String url;
    private String fileName;

    public FileService(String url) {
        this.url = url;
        this.fileName = createFileName();
    }

    private String createFileName() {
        return url.replaceAll("[\\/}{|:;.,*?=+@&^\"]", "") + ".html";
    }

    public void getHTMLPage() throws IOException {
        char[] buffer = new char[ARRAY_SIZE];
        BufferedReader siteConnection = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        BufferedWriter sitePageFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
                (new File(fileName))));
        try {
            while (siteConnection.read(buffer) != -1) {
                sitePageFile.write(buffer);

            }
        }catch (IOException e) {
            logger.error("file problems when generating HTML on disk");
        } finally {
            siteConnection.close();
            sitePageFile.close();
        }
    }

    public HashMap<String, Integer> collectDataFromHTML() throws IOException {
        ParsingService parsingService = new ParsingService();

        BufferedReader fileConnection = new BufferedReader(new InputStreamReader
                (new FileInputStream(new File(fileName))));

        char[] bufferR = new char[ARRAY_SIZE];

        HashMap<String, Integer> wordsData = new HashMap<String, Integer>();

        try {
            while (fileConnection.read(bufferR) != -1) {

                String textFromBuffer = String.valueOf(bufferR);
                String concatenatedString = parsingService.formPartOfHTML(textFromBuffer);
                StringTokenizer tokens = new StringTokenizer(concatenatedString, " \"'[]()\t\n\r,.?!;:");
                while (tokens.hasMoreTokens()) {
                    String token = tokens.nextToken().toLowerCase();
                    if (token.contains("'")) {
                        if (token.indexOf("'") == token.lastIndexOf("'") || token.indexOf("'") + 1
                                == token.lastIndexOf("'")) {
                            continue;
                        }
                    }
                    if (parsingService.isCorrectString(token)) {
                        if (wordsData.containsKey(token)) {
                            wordsData.put(token, wordsData.get(token) + 1);
                        } else {
                            wordsData.put(token, 1);
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.error("file problems when format statistic");
        } finally {
            fileConnection.close();
        }
        return wordsData;
    }

    public void putDataToFile(HashMap<String, Integer> wordsData) throws IOException {
        PrinterService printer = new PrinterService();
        BufferedWriter dataFileConnect = new BufferedWriter(new OutputStreamWriter(new FileOutputStream
                (new File(fileName + ".txt"))));
        try {
            dataFileConnect.write(printer.formatStringCount(wordsData));
            for (String s : wordsData.keySet()) {
                dataFileConnect.write(s + ": " + wordsData.get(s) + "\n");
            }
        }catch (IOException e){
            logger.error("file problems when write statistic");
        }finally {
            dataFileConnect.close();
        }

    }
}
