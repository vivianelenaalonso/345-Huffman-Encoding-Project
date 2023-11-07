import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class HuffmanTreeLetter {
	public PriorityQueue frequencyQueue;
	public char[] charArr = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 
							 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	
	public HuffmanTreeLetter() {
		frequencyQueue = new PriorityQueue();
	}
	
	public void encode(String textFile) {
		int[] frequencyArr = readFile(textFile);
		System.out.println(Arrays.toString(frequencyArr));
	}
	
	public void decode() {
		
	}
	
	private int[] readFile(String textFile) {
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
			char letterFind = fileScan.nextLine().charAt(0);
			while (fileScan.hasNext()) {
				String line = fileScan.nextLine();
				line = line.toLowerCase();
				int letter = 0;
				for (int i = 0; i < line.length(); i++) {
					frequencyArr[line.charAt(i) - 'a']++;
				}
			}
		}
		return frequencyArr;
		
	}
	
	
}
