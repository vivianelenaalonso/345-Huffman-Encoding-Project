

public class HuffmanTest {

	public static void main(String[] args) {
		HuffmanTreeLetter htl = new HuffmanTreeLetter();
		htl.encode("text1.txt");
		System.out.println();
		htl.encode("WordExample.txt");
		System.out.println();
		htl.decode("CodedText1.txt", "Codes1.txt");
	}
	
}
