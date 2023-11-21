import java.util.Scanner;

// file name: HuffmanTest.java
// author: elodie hilbert, vivian alonso
// description: console based test program for our huffman encoding implementations.

public class HuffmanTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter file name to encode: ");

		String file = scan.nextLine();

		System.out.println("Enter file name to write encoding to: ");

		String outputFile = scan.nextLine();

		System.out.println("Array or Tree based encoding?");

		String impl = scan.nextLine();

		System.out.println("Word or Letter based encoding?");

		String type = scan.nextLine();

		if (impl.equals("Array") && type.equals("Word")) {
			HuffmanArrayWord huffman = new HuffmanArrayWord();
			huffman.encode(file);
			System.out.println("Would you like to decode?");
			String decode = scan.nextLine();
			if (decode.equalsIgnoreCase("yes")) {
				huffman.decode();
			}
		} else if (impl.equals("Array") && type.equals("Letter")) {
			HuffmanArrayLetter huffman = new HuffmanArrayLetter();
			huffman.encode(file);
			System.out.println("Would you like to decode?");
			String decode = scan.nextLine();
			if (decode.equalsIgnoreCase("yes")) {
				huffman.decode();
			}
		} else if (impl.equals("Tree") && type.equals("Word")) {
			HuffmanTreeWord huffman = new HuffmanTreeWord();
			huffman.readFile(file);
			System.out.println("Would you like to decode?");
			String decode = scan.nextLine();
			if (decode.equalsIgnoreCase("yes")) {
				huffman.decode();
			}
		} else if (impl.equals("Tree") && type.equals("Letter")) {
			HuffmanTreeLetter huffman = new HuffmanTreeLetter();
			huffman.encode(file, outputFile);
			System.out.println("Would you like to decode?");
			String decode = scan.nextLine();
			if (decode.equalsIgnoreCase("yes")) {
				huffman.decode();
			}
		}

	}



}
