/*  File: HuffmanArrayWord.java
 *  Author: Kevin Li
 *  Purpose: Complete Huffman encoding by word using an array implementation
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanArrayWord {

  public String[] huffmanArr;
  private HashMap<String, Integer> wordMap;
  private HashMap<String, String> encodeMap;
  private PriorityQueue<Map.Entry<String, Integer>> pq;

  // Default constructor for HuffmanArrayWord
  public HuffmanArrayWord() {
    huffmanArr = new String[256];
    wordMap = new HashMap<>();
    encodeMap = new HashMap<>();
  }

  // Constructor for HuffmanArrayWord that takes in an input file and a command
  public HuffmanArrayWord(String inputFileName, int cmd) {
    huffmanArr = new String[256];
    wordMap = new HashMap<>();
    encodeMap = new HashMap<>();

    if (cmd == 0) {
      System.out.println("Encoding " + inputFileName);
      encode(inputFileName, "huf_" + inputFileName);
    } else if (cmd == 1) {
      decode(inputFileName, "dec_" + inputFileName, "decode.txt");
    } else {
      System.out.println("Invalid command");
    }
  }

  // Encodes input file and writes to output file
  public void encode(String inputFileName, String outputFileName) {
    readInputFile(inputFileName);

    pq =
      new PriorityQueue<>(
        wordMap.size(),
        (a, b) -> a.getValue() - b.getValue()
      );

    for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
      pq.add(entry);
    }

    huffmanArr = new String[pq.size()];
    int index = wordMap.size() - 1;
    while (pq.size() != 0) {
      huffmanArr[index] = pq.poll().getKey();
      index--;
    }

    createEncode(huffmanArr);
    writeOutputFile(inputFileName, outputFileName, encodeMap);
    writeDecodeFile(encodeMap, "decode.txt");
  }

  // Creates a code file that holds the key and binary value pairings of the Huffman encoding
  private void writeDecodeFile(
    HashMap<String, String> encodeMap2,
    String fileName
  ) {
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
    for (int i = 0; i < arr.length; i++) {
      encodeMap.put(arr[i], String.format("%s", Integer.toBinaryString(i)));
    }
  }

  // Encodes inputFileName with the Huffman code stored in encodeMap and writes to
  // outputFileName
  public void writeOutputFile(
    String inputFileName,
    String outputFileName,
    HashMap<String, String> encodeMap
  ) {
    try {
      String stringBuilder = "";

      File inputFile = new File(inputFileName);
      Scanner scanner = new Scanner(inputFile);

      while (scanner.hasNextLine()) {
        String[] line = scanner.nextLine().split(" ");

        for (int i = 0; i < line.length; i++) {
          if(line[i].equals("")){
            continue;
          }
          if (i == line.length - 1) {
            stringBuilder += encodeMap.get(line[i]);
          } else {
            stringBuilder += encodeMap.get(line[i]) + " ";
          }
        }
        stringBuilder += "\n";
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
  public void decode(
    String inputFileName,
    String outputFileName,
    String decodeFile
  ) {
    createDecodeMap(decodeFile);

    try {
      File inFile = new File(inputFileName);
      Scanner scanner = new Scanner(inFile);
      String decodeString = "";

      while (scanner.hasNext()) {
        String[] line = scanner.nextLine().split(" ");
        for (int i = 0; i < line.length; i++) {
          if (i == line.length - 1) {
            decodeString += encodeMap.get(line[i]);
          } else {
            decodeString += encodeMap.get(line[i]) + " ";
          }
        }
        decodeString += "\n";
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

  // Reads input file and stores words and how many times they appear in a HashMap
  public void readInputFile(String inputFileName) {
    try {
      File file = new File(inputFileName);

      Scanner scanner = new Scanner(file);

      while (scanner.hasNext()) {
        String word = scanner.next();
        if (wordMap.containsKey(word)) {
          wordMap.put(word, wordMap.get(word) + 1);
        } else {
          wordMap.put(word, 1);
        }
      }

      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println(inputFileName + " not found\n");
      e.printStackTrace();
    }
  }

  // Prints encode map
  public void printEncodeMap() {
    System.out.println("Printing encode map: \n");
    for (Map.Entry<String, String> entry : encodeMap.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue());
    }
  }

  // Prints word map
  public void printWordMap() {
    System.out.println("Printing word map: \n");
    for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
      System.out.println(entry.getKey() + " " + entry.getValue());
    }
  }
  
  // Prints priority queue
  public void printPQ() {
    System.out.println("Printing priority queue: \n");
    while (!pq.isEmpty()) {
      Map.Entry<String, Integer> entry = pq.poll();
      System.out.println(entry.getKey() + " " + entry.getValue());
    }
  }
}
