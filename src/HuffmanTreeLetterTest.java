
public class HuffmanTreeLetterTest {

	public static void main(String[] args) {
		testOne();
		System.out.println();
		testTwo();
		testThree();
		testFour();
		testFive();
	}

	private static void testFive() {
		System.out.println("TEST FIVE");
		System.out.println();
		HuffmanTreeLetter huffman  = new HuffmanTreeLetter();
		System.out.println("Encoding:");
		huffman.encode("files/WordAlliterationShort.txt", "SallyEncode.txt");
		System.out.println();
		System.out.println("Decoding:");
		huffman.decode();
		System.out.println();
		
	}

	private static void testFour() {
		System.out.println("TEST FOUR");
		System.out.println();
		HuffmanTreeLetter huffman = new HuffmanTreeLetter();
		System.out.println("Encoding:");
		huffman.encode("src/testMacbeth.txt", "macbethEncode.txt");
		System.out.println();
		System.out.println("Decoding:");
		huffman.decode();
		System.out.println();
	}

	private static void testThree() {
		System.out.println("TEST THREE");
		System.out.println();
		HuffmanTreeLetter huffman = new HuffmanTreeLetter();
		System.out.println("Encoding:");
		huffman.encode("src/emptyFile.txt", "empty.txt");
		System.out.println();
		System.out.println("Decoding:");
		huffman.decode();
		System.out.println();
	}

	private static void testTwo() {
		System.out.println("TEST TWO");
		System.out.println();
		HuffmanTreeLetter huffman = new HuffmanTreeLetter();
		System.out.println("Encoding:");
		huffman.encode("src/text1.txt", "text1encode.txt");
		System.out.println();
		System.out.println("Decoding:");
		huffman.decode();
		System.out.println();
		System.out.println();
	}

	private static void testOne() {
		System.out.println("TEST ONE");
		System.out.println();
		HuffmanTreeLetter huffman = new HuffmanTreeLetter();
		System.out.println("Encoding:");
		huffman.encode("src/text2.txt", "text2encode.txt");
		System.out.println();
		System.out.println("Decoding:");
		huffman.decode();
		System.out.println();
	}

}
