import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// author: elodie hilbert
// file name: HuffmanTreeLetter.java
// description: this class uses a tree implementation on huffman encoding to encode a text file by letter

public class HuffmanTreeLetter {
	private PriorityQueue pQueue;
	private HashMap<Character, Integer> frequency;
	private HashMap<Character, String> codes;
	private String encodedStr;
	private Node root;
	
	// constructor
	public HuffmanTreeLetter() {
		pQueue = new PriorityQueue();
		frequency = new HashMap<>();
		codes = new HashMap<>();
	}
	
	// creates the encoding
	public void encode(String textFile, String outputFile) {
		frequency = new HashMap<>();
		pQueue = new PriorityQueue();
		readFileFindFrequency(textFile); // read in file and create hashmap
		
		// add nodes to priority queue
		for (Character key : frequency.keySet()) {
			Node node = new Node(frequency.get(key), key);
			pQueue.add(node);
		}
		
		// create tree
		root = null;
		while (pQueue.size() > 1) {
			Node left = pQueue.pop();
			Node right = pQueue.pop();
			Node temp = new Node(left.frequency + right.frequency, '-');
			temp.left = left;
			temp.right = right;
			root = temp;
			pQueue.add(temp);
		}
		
		printEncoding(root, "");
		printEncodedText(textFile);
		writeEncodingToFile(outputFile);
	}
	
	// prints out the encoded version of the given text file
	private void printEncodedText(String textFile) {
		encodedStr = "";
		Scanner fileScan = null;
		try {
			fileScan = new Scanner(new File(textFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fileScan == null) {
			System.out.println("File does not exist");
		} else {
			while (fileScan.hasNext()) {
				String line = fileScan.nextLine();
				line = line.toLowerCase();
				for (int i = 0; i < line.length(); i++) { // for each char find code and print
					System.out.print(codes.get(line.charAt(i)));
					encodedStr += codes.get(line.charAt(i)); // create a str with encoded text for decoding
				}
			}
			System.out.println();
		}
		
	}

	// goes through the encoded string and looks for a leaf node with a char
	// starting back at the root once one is found
	public void decode() {
		Node curr = root;
		for (int i = 0; i < encodedStr.length(); i++) {
			if (encodedStr.charAt(i) == '0') { // go left
				curr = curr.left;
			} else { // go right
				curr = curr.right;
			}
			
			if (curr.left == null && curr.right == null) { // found leaf
				System.out.print(curr.character);
				curr = root; // start over
			}
		}
	}


	// this method reads in the file and adds each letter to a hashmap
	// and increments the associated value however many times that letter 
	// is seen.
	private void readFileFindFrequency(String textFile) {
		Scanner fileScan = null;
		try {
			fileScan = new Scanner(new File(textFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (fileScan == null) {
			System.out.println("File does not exist");
		} else {
			while (fileScan.hasNext()) {
				String line = fileScan.nextLine();
				line = line.toLowerCase();
				for (int i = 0; i < line.length(); i++) {
					if (frequency.containsKey(line.charAt(i))) {
						frequency.put(line.charAt(i), frequency.get(line.charAt(i)) + 1);
					} else {
						frequency.put(line.charAt(i), 1);
					}
				}
			}
		}
		
	}
	
	// recursively iterates through the tree and prints the code associated with each letter
	private void printEncoding(Node node, String output) {
		if (node.left == null && node.right == null) { // if lead node
		      System.out.println(node.character + ":" + output);
		      codes.put(node.character, output);
		      return;
		    }

		    printEncoding(node.left, output + "0");
		    printEncoding(node.right, output + "1");
	}
	
	/**
	 * Writes the encoded text to an output file. 
	 * 
	 * @param String Input file to write to.
	 * @param String Output file to write to.
	 * @param Map<String, String> Huffman codes map.
	 */
	public void writeEncodingToFile(String outputFileName) {
		Scanner scan = new Scanner(System.in);
        File outputFile = new File(outputFileName);
        try {
            outputFile.createNewFile();

            FileWriter writer = new FileWriter(outputFile);
            writer.write(encodedStr);
            writer.close();

        } catch (Exception e) {
            System.out.println("Error creating file.");
            e.printStackTrace();
        }

        scan.close();
	}
	
	
	
}
