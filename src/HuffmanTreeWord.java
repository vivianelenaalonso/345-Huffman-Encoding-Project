
/**
* @author Vivian Elena Alonso
* @purpose Huffman Encoding by word frequency using a tree structure. 
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class HuffmanTreeWord {
	private PriorityQueue priorityQueue;
	private Map<String, Integer> frequencyMap;
	private Map<String, String> huffmanCodes;
	private StringBuilder encodedText;
	private StringBuilder decodedText;

	/**
	 * Constructor
	 */
	HuffmanTreeWord() {
		priorityQueue = new PriorityQueue();
		frequencyMap = new HashMap<>();
		huffmanCodes = new HashMap<>();
	}

	/**
	 * Collects the file name from keyboard and organizes the lines of data.
	 * 
	 * @param fileName String name of the the file to read from.
	 */
	void readFile(String fileName) {
		StringBuilder fileText = new StringBuilder();
		try {
			Scanner file = new Scanner(new File(fileName));
			while (file.hasNextLine()) {
				String sentence = file.nextLine();
				sentence = sentence.strip();
				fileText.append(sentence);
				fileText.append(" ");
				// List of words
				String[] words = sentence.split(" ");
				for (int index = 0; index < words.length; index++) {
					if (frequencyMap.containsKey(words[index])) {
						frequencyMap.put(words[index], frequencyMap.get(words[index]) + 1);
					} else {
						frequencyMap.put(words[index], 1);
					}
				}
			}
			file.close();
			makeEncoding();
			// Print debug
			printCodes();
			huffmanEncode(fileText.toString().trim());
			// Print debug
			printEncode();
		} catch (FileNotFoundException exception) {
			System.out.println("File was not found.");
			exception.printStackTrace();
		}
	}

	/**
	 * Encodes the given string.
	 * 
	 * @param text
	 */
	void huffmanEncode(String text) {
		encodedText = new StringBuilder();
		String[] words = text.split(" ");
		for (String word : words) {
			if (huffmanCodes.containsKey(word)) {
				encodedText.append(huffmanCodes.get(word));
				encodedText.append(" ");
			} else {
				// Unknown words
				encodedText.append("UNKNOWN: " + word);
				encodedText.append(" ");
			}
		}
	}

	/**
	 * Creates the Huffman code from the frequency of words in the given text file.
	 */
	void makeEncoding() {
		// Add values to priority queue.
		for (Map.Entry<String, Integer> frequency : frequencyMap.entrySet()) {
			priorityQueue.add(new Node(frequency.getValue(), frequency.getKey()));
		}

		Node root = buildTree();

		// Huffman codes
		generateHuffmanCodes(root, "", huffmanCodes);
	}

	/**
	 * Generates the huffman codes.
	 * 
	 * @param node         Root node.
	 * @param code         Code string for each Node.
	 * @param huffmanCodes Map of the words and their Huffman codes.
	 */
	void generateHuffmanCodes(Node node, String code, Map<String, String> huffmanCodes) {
		if (node != null) {
			if (node.word != null) {
				huffmanCodes.put(node.word, code);
			}
			// Left nodes are zero, right nodes are one
			generateHuffmanCodes(node.left, code + "0", huffmanCodes);
			generateHuffmanCodes(node.right, code + "1", huffmanCodes);
		}
	}

	/**
	 * Uses the word frequency from the priority queue to build a Huffman Tree.
	 * 
	 * @return the Huffman Tree Root Node.
	 */
	Node buildTree() {
		while (priorityQueue.size() > 1) {
			Node left = priorityQueue.pop();
			Node right = priorityQueue.pop();
			Node node = new Node(left.frequency + right.frequency, null);
			node.left = left;
			node.right = right;
			priorityQueue.add(node);
		}
		return priorityQueue.peek();
	}

	/**
	 * Decodes the given string.
	 */
	void huffmanDecode() {
		decodedText = new StringBuilder();
		String[] encodedWords = encodedText.toString().split(" ");

		for (String encodedWord : encodedWords) {
			if (encodedWord.substring(0).equals("U")) {
				String unknownWord = encodedWord.substring("UNKNOWN: ".length());
				decodedText.append(unknownWord).append(" ");
			} else {
				String decodedWord = decodeWord(encodedWord);
				decodedText.append(decodedWord).append(" ");
			}
		}
		printDecode();
	}

	/**
	 * Decodes a single word using the Huffman codes.
	 *
	 * @param code Huffman code of the word.
	 * @return Decoded word.
	 */
	private String decodeWord(String code) {
		for (Map.Entry<String, String> entry : huffmanCodes.entrySet()) {
			if (entry.getValue().equals(code)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Prints the Huffman Codes for debugging.
	 */
	void printCodes() {
		System.out.println("Codes: ");
		for (Map.Entry<String, String> frequency : huffmanCodes.entrySet()) {
			System.out.println(frequency.getKey() + " : " + frequency.getValue());
		}
	}

	/**
	 * Prints the created tree in in-order traversal.
	 * 
	 */
	void printTree() {

	}

	/**
	 * Prints the encoded string.
	 * 
	 */
	void printEncode() {
		String eText = encodedText.toString().trim();
		System.out.println("Encoded text: ");
		System.out.println(eText);
	}

	/**
	 * Prints the decoded string.
	 * 
	 */
	void printDecode() {
		String eText = decodedText.toString().trim();
		System.out.println();
		System.out.println("Decoded text: ");
		System.out.println(eText);
	}
}
