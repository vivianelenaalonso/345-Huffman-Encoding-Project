import java.util.Scanner;

public class HuffmanTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

	    System.out.println("Encode or Decode? ");
	    
	    String codeType = scan.nextLine();
	    
	    if (codeType.equals("Encode")) {
	    	getEncode();
	    } else if (codeType.equals("Decode")) {
	    	getDecode();
	    }

//		HuffmanTreeLetter htl = new HuffmanTreeLetter();
//		htl.encode("text1.txt");
//		System.out.println();
//		htl.encode("WordExample.txt");
//		System.out.println();
	}

	private static void getEncode() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter file name to encode: ");
		
		String file = scan.nextLine();
		
		System.out.println("Array or Tree based encoding?");
		
		String impl = scan.nextLine();
		
		System.out.println("Word or Letter based encoding?");
		
		String type = scan.nextLine();
		
		if (impl.equals("Array") && type.equals("Word")) {
			HuffmanArrayWord huffman = new HuffmanArrayWord();
			huffman.encode(file);
		} else if (impl.equals("Array") && type.equals("Letter")) {
			HuffmanArrayLetter huffman = new HuffmanArrayLetter();
			huffman.encode(file);
		} else if (impl.equals("Tree") && type.equals("Word")) {
			HuffmanTreeWord huffman = new HuffmanTreeWord();
			huffman.encode(file);
		} else if (impl.equals("Tree") && type.equals("Letter")) {
			HuffmanTreeLetter huffman = new HuffmanTreeLetter();
			huffman.encode(file);
		}
		
	}

	private static void getDecode() {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter file name with codes: ");
		
		String codesFile = scan.nextLine();
		
		System.out.println("Enter file name to decode: ");
		
		String file = scan.nextLine();
		
		System.out.println("Array or Tree based decoding?");
		
		String impl = scan.nextLine();
		
		System.out.println("Word or Letter based decoding?");
		
		String type = scan.nextLine();
		
		if (impl.equals("Array") && type.equals("Word")) {
			HuffmanArrayWord huffman = new HuffmanArrayWord();
			huffman.decode(codesFile, file);
		} else if (impl.equals("Array") && type.equals("Letter")) {
			HuffmanArrayLetter huffman = new HuffmanArrayLetter();
			huffman.decode(codesFile, file);
		} else if (impl.equals("Tree") && type.equals("Word")) {
			HuffmanTreeWord huffman = new HuffmanTreeWord();
			huffman.decode(codesFile, file);
		} else if (impl.equals("Tree") && type.equals("Letter")) {
			HuffmanTreeLetter huffman = new HuffmanTreeLetter();
			huffman.decode(codesFile, file);
		}
		
	}
	
}
