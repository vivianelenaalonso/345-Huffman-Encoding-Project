import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanTreeLetter {
	public PriorityQueue pQueue;
	public HashMap<Character, Integer> frequency;
	
	public HuffmanTreeLetter() {
		pQueue = new PriorityQueue();
		frequency = new HashMap<>();
	}
	
	public void encode(String textFile) {
		readFile(textFile); // read in file and create hashmap
		
		// add nodes to priority queue
		for (char key : frequency.keySet()) {
			Node node = new Node(frequency.get(key), key);
			pQueue.add(node);
		}
		
		// create tree
		Node root = null;
		while (pQueue.size() > 1) {
			Node min = pQueue.poll();
			Node secondMin = pQueue.poll();
			Node temp = new Node(min.frequency + secondMin.frequency, '-');
			temp.left = min;
			temp.right = secondMin;
			root = temp;
			pQueue.add(temp);
		}
		
		printEncoding(root, "");
	}
	
	public void decode() {
		
	}
	
	private void readFile(String textFile) {
		int[] frequencyArr = new int[26];
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
	
	private void printEncoding(Node node, String output) {
		if (node.left == null && node.right == null) {
		      System.out.println(node.character + "   |  " + output);
		      return;
		    }

		    printEncoding(node.left, output + "0");
		    printEncoding(node.right, output + "1");
	}
	
	
}
