import java.util.Scanner;

// file name: HuffmanTest.java
// author: elodie hilbert, vivian alonso
// description: console based test program for our huffman encoding implementations.

public class HuffmanTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean fos;
		String file;

		System.out.println("Welcome to the Huffman Encoding Program!");
		System.out.println("Would you like to open a file or enter a string?");

		String fileorstring = scan.nextLine();

		if (fileorstring.toLowerCase().equals("string")){
			fos = false;
		} else {
			fos = true;
		}

		if (fos == true){
			System.out.println("Enter file name to encode: ");
			file = scan.nextLine();}

		else{
			
			System.out.println("Enter string to encode: ");
			file = scan.nextLine();
		}

		System.out.println("Enter file name to write encoding to: ");

		String outputFile = scan.nextLine();

		System.out.println("Array or Tree based encoding?");

		String impl = scan.nextLine();

		System.out.println("Word or Letter based encoding?");

		String type = scan.nextLine();
		
		if (impl.toLowerCase().equals("array") && type.toLowerCase().equals("word")) {
			HuffmanArrayWord huffman = new HuffmanArrayWord();
			huffman.encode(file, outputFile);
			System.out.println("Would you like to decode?");
			String decode = scan.nextLine();
			if (decode.equalsIgnoreCase("yes")) {
				huffman.decode(outputFile, "dec_" + outputFile, "decode.txt");
			}
		} else if (impl.toLowerCase().equals("array") && type.toLowerCase().equals("letter")) {
			HuffmanArrayLetter huffman = new HuffmanArrayLetter(file, fos);
			huffman.exportHuffmanFile(outputFile);
			System.out.println("Would you like to decode?");
			String decode = scan.nextLine();
			if (decode.equalsIgnoreCase("yes")) {
				huffman.decode(outputFile, "dec_" + outputFile, outputFile+"key.txt");
			}
		} else if (impl.toLowerCase().equals("tree") && type.toLowerCase().equals("word")) {
			HuffmanTreeWord huffman = new HuffmanTreeWord();
			huffman.readFile(file);
			huffman.writeEncodeToFile(outputFile);
			System.out.println("Would you like to decode?");
			String decode = scan.nextLine();
			if (decode.equalsIgnoreCase("yes")) {
				huffman.huffmanDecode();
			}
		} else if (impl.toLowerCase().equals("tree") && type.toLowerCase().equals("letter")) {
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
