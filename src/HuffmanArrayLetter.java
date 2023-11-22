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

    public HuffmanArrayLetter(String filename, boolean isFile) {
        priorityQueue = new PriorityQueue();
        frequencyMap = new HashMap<>();
        huffmanCodes = new HashMap<>();
        encodeMap = new HashMap<>();
        temp = new MaxHeap();
        if (isFile) {
            readFileToEncode(filename);
        } else {
            readStringtoEncode(filename);
        }
    }
    public void readFiletoDecode(String filenameencoded, String filenamekey){
        //assuming that the file is in the format of the encoded text as well as a key in the format of A:10101 every line
    }

    public String readStringtoEncode(String string){
        for (int i = 0; i < string.length(); i++) {
            if (frequencyMap.containsKey(String.valueOf(string.charAt(i)))) {
                frequencyMap.put(String.valueOf(string.charAt(i)), frequencyMap.get(String.valueOf(string.charAt(i))) + 1);
            }
            else {
                frequencyMap.put(String.valueOf(string.charAt(i)), 1);
            }
        }
        createPriorityQueue();
        createMaxHeap();
        createHuffmanTree();
        createHuffmanMap("", 0);
        createHuffmanCodes(string);
        System.out.println("The string to encode is: " + string);
        System.out.println("The string is " + string.length()*8 + " bits long.");
        System.out.println("Huffman Code Key:" + huffmanCodes.toString());
        System.out.println("Encoded Text:" + encodedText);
        System.out.println("Encoded Text is: " + encodedText.length() + " bits long.");
        System.out.println("The compression ratio is: " + (double)encodedText.length()/(double)(string.length()*8));
        return encodedText;
    }

    public void readFileToEncode(String filename){
        try {
            Scanner file = new Scanner(new File(filename));
            String totalline = "";
            while (file.hasNextLine()) {
                String line = file.nextLine();
                totalline += (line + "|"); //bad solution, but i'm going to use | as the newline character here.
                }
            for (int i = 0; i < totalline.length(); i++) {
                if (frequencyMap.containsKey(String.valueOf(totalline.charAt(i)))) {
                    frequencyMap.put(String.valueOf(totalline.charAt(i)), frequencyMap.get(String.valueOf(totalline.charAt(i))) + 1);
                }
                else {
                    frequencyMap.put(String.valueOf(totalline.charAt(i)), 1);
                }
            }
            file.close();
            createPriorityQueue();
            createMaxHeap();
            createHuffmanTree();
            createHuffmanMap("", 0);
            createHuffmanCodes(totalline);
            System.out.println("The string to encode is: " + totalline.replace("|", "\n"));
            System.out.println("The string is " + totalline.length()*8 + " bits long.");
            System.out.println("Huffman Code Key:" + huffmanCodes.toString());
            System.out.println("Encoded Text:" + encodedText);
            System.out.println("Encoded Text is: " + encodedText.length() + " bits long.");
            System.out.println("The compression ratio is: " + (double)encodedText.length()/(double)(totalline.length()*8));
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

    createDecodeMap(decodeFile);

    try {
      File inFile = new File(inputFileName);
      Scanner scanner = new Scanner(inFile);
      String decodeString = "";
      int index = 0;
      while (scanner.hasNext()) {
        String line = scanner.nextLine();
        for (int i = 0; i < line.length(); i++) {
          if (line.charAt(i) == '0') {
            index = index*2+1;
          } else if (line.charAt(i) == '1') {
            index = index*2+2;
          }
          if (heap.get(index).word != "internal"){
                if (heap.get(index).word.equals("|")){
                    decodeString += "\n";
                }
                else{
                    decodeString += heap.get(index).word;
                }
                index = 0;
          }
        }
      }
      System.out.println(decodeString);

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
      scanner.close();
    } catch (FileNotFoundException e) {
      System.out.println(decodeFile + " not found");
      e.printStackTrace();
    }
  }

    public void createHuffmanTree(){
        int index = 0;
        //create the initial array, which is size #of nodes * 2 - 1
        heap = new ArrayList<Node>(Arrays.asList(new Node[256*256]));
        while (priorityQueue.size() > 1){
            Node left = priorityQueue.pop();
            Node right = priorityQueue.pop();
            Node internal = new Node(left.frequency + right.frequency, "internal");
            internal.left = left;
            internal.right = right;
            priorityQueue.add(internal);
            temp.add(internal);
        }
        Node root = temp.pop();
        heap.set(index,root);
        createArrayTree(root.left, index*2+1);
        createArrayTree(root.right, index*2+2);
        }


    public void createArrayTree(Node root, int index){
        if (root.right == null && root.left == null) {
            heap.set(index, root);
        }else{
            heap.set(index,root);
            createArrayTree(root.left, index*2+1);
            createArrayTree(root.right, index*2+2);
        }
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