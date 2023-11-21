import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class HuffmanArrayLetter {
    private PriorityQueue priorityQueue;
	private Map<String, Integer> frequencyMap;
	private Map<String, String> huffmanCodes;
	private String encodedText;
	private String decodedText;
    private ArrayList<Node> heap;
    private MaxHeap temp;
    private int frequency = 0;

    public HuffmanArrayLetter(String filename) {
        priorityQueue = new PriorityQueue();
        frequencyMap = new HashMap<>();
        huffmanCodes = new HashMap<>();
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
                for (char letter : letters) {
                    if (frequencyMap.containsKey(String.valueOf(letter))) {
                        frequencyMap.put(String.valueOf(letter), frequencyMap.get(String.valueOf(letter)) + 1);
                    }
                    else {
                        frequencyMap.put(String.valueOf(letter), 1);
                    }
                }

                }
            file.close();
            createPriorityQueue();
            createMaxHeap();
            createHuffmanTree();
            System.out.println(heap);
            createHuffmanMap("", 0);
            System.out.println(huffmanCodes);
            createHuffmanCodes(totalline);
            System.out.println(encodedText);
            }
            catch (FileNotFoundException exception) {
            System.out.println("File was not found.");
            exception.printStackTrace();

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

    public void createHuffmanTree(){
        int index = 0;
        //create the initial array, which is size #of nodes * 2 - 1
        heap = new ArrayList<>(Arrays.asList(new Node[priorityQueue.size()*priorityQueue.size()]));
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



