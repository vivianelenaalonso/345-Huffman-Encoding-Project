import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class HuffmanArrayLetter {
    private PriorityQueue priorityQueue;
	private Map<String, Integer> frequencyMap;
	private Map<String, String> huffmanCodes;
    private HashMap<String, String> encodeMap;
	private String encodedText;
	private String decodedText;
    private ArrayList<Node> heap;
    private MaxHeap temp;
    private int frequency = 0;

    public HuffmanArrayLetter(String filename) {
        priorityQueue = new PriorityQueue();
        frequencyMap = new HashMap<>();
        huffmanCodes = new HashMap<>();
        encodeMap = new HashMap<>();
        temp = new MaxHeap();
        readFileToEncode(filename);
    }
    public void readFiletoDecode(String filenameencoded, String filenamekey){
        //assuming that the file is in the format of the encoded text as well as a key in the format of A:10101 every line
    }
    public void readFileToEncode(String filename){
        try {
            Scanner file = new Scanner(new File(filename));
            String totalline = "";
            while (file.hasNextLine()) {
                String line = file.nextLine();
                totalline += line;
                char[] letters = line.toCharArray();
                for (int i = 0; i < letters.length; i++) {
                    if (frequencyMap.containsKey(String.valueOf(letters[i]))) {
                        frequencyMap.put(String.valueOf(letters[i]), frequencyMap.get(String.valueOf(letters[i])) + 1);
                    }
                    else {
                        frequencyMap.put(String.valueOf(letters[i]), 1);
                    }
                }
                
                }
            file.close();
            createPriorityQueue();
            createMaxHeap();
            createHuffmanTree();
            createHuffmanMap("", 0);
            createHuffmanCodes(totalline);
            }
            catch (FileNotFoundException exception) {
            System.out.println("File was not found.");
            exception.printStackTrace();
            
        }
        
        }

    public void exportHuffmanFile(String filename){
        try {
            File file = new File(filename);
            file.createNewFile();
            PrintWriter writer = new PrintWriter(file);
            writer.write(encodedText);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            File mapfile = new File(filename+"key.txt");
            mapfile.createNewFile();
            FileWriter writer = new FileWriter(mapfile);
            for (Map.Entry<String, String> entry : huffmanCodes.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue() + "\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error creating file");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error creating file");
            e.printStackTrace();
        }
    }

    
    public void createPriorityQueue(){
        for (String key : frequencyMap.keySet()) {
            Node n = new Node(frequencyMap.get(key), key);
            frequency += frequencyMap.get(key);
            priorityQueue.add(n);
        }
    }
    public void createMaxHeap(){
        for (String key : frequencyMap.keySet()) {
            Node n = new Node(frequencyMap.get(key), key);
            temp.add(n);
        }
    }

    public void decode(String inputFileName, String outputFileName, String decodeFile) {
    System.out.println(inputFileName);
    System.out.println(outputFileName);
    System.out.println(decodeFile);

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
        String[] line = scanner.nextLine().split(":");
        String word = line[0];
        String code = line[1];
        encodeMap.put(code, word);
      }
      System.out.println(encodeMap.toString());
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println(decodeFile + " not found");
      e.printStackTrace();
    }
  }

    public void createHuffmanTree(){
        int index = 0;
        //create the initial array, which is size #of nodes * 2 - 1
        heap = new ArrayList<Node>(Arrays.asList(new Node[128 * 128]));
        while (temp.size() > 2) {
            Node left = temp.pop();
            Node internal = new Node(frequency, "internal");
            heap.set(index, internal);
            heap.set(index*2+1, left);
            index = index*2+2;
            frequency -= left.frequency;
        }
        Node left = temp.pop();
        Node right = temp.pop();
        Node internal = new Node(frequency, "internal");
        heap.set(index, internal);
        heap.set(index*2+1, left);
        heap.set(index*2+2, right);
    }
    public void createHuffmanMap(String code, int index){
        //implement later
        if (heap.get(index).word != "internal"){
            huffmanCodes.put(heap.get(index).word, code);
        }
        else{
            createHuffmanMap(code + "0", 2*index + 1);
            createHuffmanMap(code + "1", 2*index + 2);
        }
        
    }
    public void createHuffmanCodes(String line){
        //implement later
        encodedText = "";
        for (int i = 0; i < line.length(); i++) {
            encodedText += huffmanCodes.get(String.valueOf(line.charAt(i)));
        }
    }
}