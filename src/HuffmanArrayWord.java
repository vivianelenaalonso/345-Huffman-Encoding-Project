/*  File: HuffmanArrayWord.java
 *  Author: Kevin Li
 *  Purpose: 
 */

import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class HuffmanArrayWord {

    public String[] huffmanArr;
    private HashMap<String, Integer> wordMap;
    private PriorityQueue<String[]> pq;
    private int wordCount;

    public HuffmanArrayWord(String inputFileName, int cmd) {
        huffmanArr = new String[256];
        wordMap = new HashMap<String, Integer>();
        wordCount = 0;

        if (cmd == 0) {
            encode(inputFileName, inputFileName + ".huf");
        } else if (cmd == 1) {
            decode(inputFileName, inputFileName + ".dec");
        } else {
            System.out.println("Invalid command");
        }
    }


    // Encodes input file and writes to output file
    public void encode(String inputFileName, String outputFileName) {
        readInputFile(inputFileName);
        pq = new PriorityQueue<String[]>(wordCount);

        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();
            pq.add(new String[]{word, Integer.toString(frequency)});
        }

        for(int i = 0; i < wordCount; i++) {
            String[] temp = pq.poll();
            System.out.println(temp[0] + ": " + temp[1]);
        }
    }

    public void decode(String inputFileName, String outputFileName) {

    }

    public void printFrequencyTable() {

    }


    // Reads input file and stores words and how many times they appear in a HashMap
    public void readInputFile(String inputFileName) {

        Scanner scanner = new Scanner(inputFileName);
            
        while (scanner.hasNext()) {
            String word = scanner.next();
            if (wordMap.containsKey(word)) {
                wordMap.put(word, wordMap.get(word) + 1);
                wordCount++;
            } else {
                wordMap.put(word, 1);
            }
        }

        scanner.close();

    }


}
