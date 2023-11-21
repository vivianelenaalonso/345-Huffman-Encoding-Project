/**
 * @author Vivian Elena Alonso
 *
 * @purpose To test the Huffman Tree Class done by tree for words.
 *
 */
public class TreeWordTest {
	public static void main(String[] args) {
        testOne();
        testTwo();
        testThree();
        testFour();
        testFive();
        testSix();
        testSeven();
        testEight();  
    }

    // Short alliteration example
    public static void testOne() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("files/WordAlliterationShort.txt");
        huffmanTree.huffmanDecode();
        huffmanTree.printDecode();
    }
    
    // Long alliteration example
    public static void testTwo() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("files/WordAlliterationLong.txt");
        huffmanTree.huffmanDecode();
        huffmanTree.printDecode();
    }
    
    // One word repeated many times
    public static void testThree() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("files/SingleWordRepeat.txt");
        huffmanTree.huffmanDecode();
    }
    
    // Empty file (code check)
    public static void testFour() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("files/TreeWordEmptyFile.txt");
        huffmanTree.huffmanDecode();
    }
    
    // No repeating words
    public static void testFive() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("files/TreeWordNoRepeats.txt");
        huffmanTree.huffmanDecode();
    }
    
    // Many repeating words 
    public static void testSix() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("files/TreeWordManyRepeats.txt");
        huffmanTree.huffmanDecode();
    }

    // Random characters and symbols
    public static void testSeven() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("files/WordTreeSymbols.txt");
        huffmanTree.huffmanDecode(); 
    }
    
    // Mix of letters, numbers and symbols
    public static void testEight() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("files/TreeWordMixedLong.txt");
        huffmanTree.huffmanDecode();  
    }
}
