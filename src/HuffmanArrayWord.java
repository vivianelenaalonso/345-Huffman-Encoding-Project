/*  File: HuffmanArrayWord.java
 *  Author: Kevin Li
 *  Purpose: 
 */

import java.util.PriorityQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HuffmanArrayWord {

    public String[] huffmanArr;
    private HashMap<String, Integer> wordMap;
    private HashMap<String, String> encodeMap;
    private PriorityQueue<Map.Entry<String, Integer>> pq;

    private int wordCount;

    public HuffmanArrayWord(String inputFileName, int cmd) {
        huffmanArr = new String[256];
        wordMap = new HashMap<String, Integer>();
        encodeMap = new HashMap<String, String>();
        wordCount = 0;

        if (cmd == 0) {
            System.out.println("Encoding " + inputFileName);
            encode(inputFileName, inputFileName + ".huf");
        } else if (cmd == 1) {
            decode(inputFileName, inputFileName + ".dec", "decode.txt");
        } else {
            System.out.println("Invalid command");
        }
    }

    // Encodes input file and writes to output file
    public void encode(String inputFileName, String outputFileName) {
        readInputFile(inputFileName);

        // Create priority queue
        pq = new PriorityQueue<>(wordMap.size(), (a, b) -> a.getValue() - b.getValue());

        // Add all words to priority queue
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            pq.add(entry);
        }

        // Create Huffman Array
        huffmanArr = new String[pq.size()];
        int index = 0;
        while (pq.size() != 0) {
            huffmanArr[index] = pq.poll().getKey();
            index++;
        }

        createEncode(huffmanArr);

        // Write to output file
        writeOutputFile(inputFileName, outputFileName, encodeMap);
        
        // Write decode file
        writeDecodeFile(encodeMap, "decode.txt");
    }

    private void writeDecodeFile(HashMap<String, String> encodeMap2, String fileName) {
        try {
            File file = new File(fileName);
            file.createNewFile();

            FileWriter writer = new FileWriter(file);

            for (Map.Entry<String, String> entry : encodeMap2.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error creating file");
            e.printStackTrace();
        }
    }

    // Takes an array of words and maps them to binary strings that represent their
    // index in the array
    public void createEncode(String[] arr) {
        int binarySize = (int) Math.ceil(Math.log(arr.length) / Math.log(2));

        for (int i = 0; i < arr.length; i++) {
            encodeMap.put(arr[i], String.format("%" + binarySize + "s", Integer.toBinaryString(i)).replace(' ', '0'));
        }
    }

    // Encodes inputFileName with the Huffman code stored in encodeMap and writes to
    // outputFileName
    public void writeOutputFile(String inputFileName, String outputFileName, HashMap<String, String> encodeMap) {
        try {
            String stringBuilder = "";

            File inputFile = new File(inputFileName);
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNext()) {
                String word = scanner.next();
                stringBuilder += encodeMap.get(word);
            }

            File outputFile = new File(outputFileName);
            try {
                outputFile.createNewFile();
                
                FileWriter writer = new FileWriter(outputFile);
                writer.write(stringBuilder);
                writer.close();

            } catch (Exception e) {
                System.out.println("Error creating file");
                e.printStackTrace();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(inputFileName + " not found");
            e.printStackTrace();
        }
    }


    // Decodes input file and writes to output file
    public void decode(String inputFileName, String outputFileName, String decodeFile) {

        createDecodeMap(decodeFile);

        try {
            File inFile = new File(inputFileName);
            Scanner scanner = new Scanner(inFile);
            String decodeString = "";

            while (scanner.hasNext()) {
                String code = scanner.next();
                System.out.println(code);
                decodeString += encodeMap.get(code);
            }

            File outFile = new File(outputFileName);

            try {
                outFile.createNewFile();

                FileWriter writer = new FileWriter(outFile);
                writer.write(decodeString);
                writer.close();

            } catch (IOException e) {
                System.out.println("Error creating file");
                e.printStackTrace();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(inputFileName + " not found");
            e.printStackTrace();
        }
    }

    
    public void createDecodeMap(String decodeFile) {
        try {
            File file = new File(decodeFile);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String word = scanner.next();
                String code = scanner.next();
                encodeMap.put(code, word);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(decodeFile + " not found");
            e.printStackTrace();
        }
    }

    // Prints priority queue
    public void printPQ() {
        System.out.println("Printing priority queue");
        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> entry = pq.poll();
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    // Prints encode map
    public void printEncodeMap() {
        System.out.println("Printing encode map");
        for (Map.Entry<String, String> entry : encodeMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    // Prints word map
    public void printWordMap() {
        System.out.println("Printing word map: ");
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    // Reads input file and stores words and how many times they appear in a HashMap
    public void readInputFile(String inputFileName) {
        try {
            File file = new File(inputFileName);

            Scanner scanner = new Scanner(file);

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
        } catch (FileNotFoundException e) {
            System.out.println(inputFileName + " not found");
            e.printStackTrace();
        }

    }

}
