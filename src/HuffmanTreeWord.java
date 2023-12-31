/**
* @author Vivian Elena Alonso
* @purpose Huffman Encoding by word frequency using a tree structure. 
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class HuffmanTreeWord {
	private PriorityQueue priorityQueue;
	private Map<String, Integer> frequencyMap;
	private Map<String, String> huffmanCodes;
	private StringBuilder encodedText;
	private StringBuilder decodedText;
	private String writeEncodeFile;

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
				for (int index = 0; index < words.length; index++) {
					// Add the words to a frequency map
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
			System.out.println();
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
	 * Writes the huffman encoded string to an output file. 
	 * 
	 * @param encodedText String of encoded text.
	 * @param writeEncodeFile Output file to write to.
	 */
	public void writeEncodeToFile(String writeEncodeFile) {
		// Write encoding to a file
        try {
            File outputFile = new File(writeEncodeFile);
            outputFile.createNewFile();

            FileWriter writer = new FileWriter(outputFile);
            writer.write(encodedText.toString().trim());
            writer.close();

            System.out.println("Encoded text has been written to: " + writeEncodeFile);

        } catch (IOException e) {
            System.out.println("Error writing to file: " + writeEncodeFile);
            e.printStackTrace();
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
	 * This method takes a file, decodes it, and writes it
	 * to an output file.
	 */
	public void decodeFile(String decodeFile, String inputFileName, String outputFileName) {
		try {
            File file = new File(decodeFile);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String word = scanner.next();
                String code = scanner.next();
                huffmanCodes.put(code, word);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(decodeFile + " not found");
            e.printStackTrace();
        }
		
        try {
            File inFile = new File(inputFileName);
            Scanner scanner = new Scanner(inFile);
            String decodeString = "";

            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(" ");
                for (int i = 0; i < line.length; i++) {
                    if (i == line.length - 1) {
                        decodeString += huffmanCodes.get(line[i]);
                    } else {
                        decodeString += huffmanCodes.get(line[i]) + " ";
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
