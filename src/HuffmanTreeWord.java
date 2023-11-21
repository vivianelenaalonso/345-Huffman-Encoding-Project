/**
* @author Vivian Elena Alonso
* @purpose Huffman Encoding by word frequency using a tree structure.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
	public  void readFile(String fileName) {
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
				for (String word : words) {
					// Add the words to a frequency map
					if (frequencyMap.containsKey(word)) {
						frequencyMap.put(word, frequencyMap.get(word) + 1);
					} else {
						frequencyMap.put(word, 1);
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
	public void huffmanEncode(String text) {
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
	public void makeEncoding() {
		// Add values to priority queue.
		for (Map.Entry<String, Integer> frequency : frequencyMap.entrySet()) {
			priorityQueue.add(new Node(frequency.getValue(), frequency.getKey()));
		}

		// Build the huffman tree
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
	public void generateHuffmanCodes(Node node, String code, Map<String, String> huffmanCodes) {
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
	public Node buildTree() {
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
	public void huffmanDecode() {
		decodedText = new StringBuilder();
		String[] encodedWords = encodedText.toString().split(" ");

		for (String encodedWord : encodedWords) {
			if (encodedWord.substring(0).equals("U")) {
				String unknownWord = encodedWord.substring("U".length());
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
	public String decodeWord(String code) {
		for (Map.Entry<String, String> entry : huffmanCodes.entrySet()) {
			if (entry.getValue().equals(code)) {
				return entry.getKey();
			}
		}
		return null;
	}

	/**
	 * Writes the encoded text to an output file.
	 *
	 * @param String Input file to write to.
	 * @param String Output file to write to.
	 * @param Map<String, String> Huffman codes map.
	 */
	public void writeEncodingToFile(String inputFileName, String outputFileName, Map<String, String> huffmanCodes) {
        // Very similar to Kevin's because he initially had the idea to write the encoding to file.
		try {
            String stringBuilder = "";

            File inputFile = new File(inputFileName);
            Scanner scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(" ");

                for (int i = 0; i < line.length; i++) {
                    if (i == line.length - 1) {
                        stringBuilder += huffmanCodes.get(line[i]);
                    } else {
                        stringBuilder += huffmanCodes.get(line[i]) + " ";
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
                System.out.println("Error creating file.");
                e.printStackTrace();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(inputFileName + " not found.");
            e.printStackTrace();
        }
	}

	/**
	 * Prints the Huffman Codes for debugging.
	 */
	public void printCodes() {
		System.out.println("Codes: ");
		for (Map.Entry<String, String> frequency : huffmanCodes.entrySet()) {
			System.out.println(frequency.getKey() + " : " + frequency.getValue());
		}
	}

	/**
	 * Prints the created tree in in-order traversal.
	 *
	 * @param node Root node of the tree created.
	 */
	public void printTree(Node node) {
		if (node != null) {
			printTree(node.left);
			System.out.println(node.toString());
			printTree(node.right);
		}
	}

	/**
	 * Prints the encoded text.
	 *
	 */
	public void printEncode() {
		String eText = encodedText.toString().trim();
		System.out.println("Encoded text: ");
		System.out.println(eText);
	}

	/**
	 * Prints the decoded text.
	 *
	 */
	public void printDecode() {
		String eText = decodedText.toString().trim();
		System.out.println();
		System.out.println("Decoded text: ");
		System.out.println(eText);
	}
}
