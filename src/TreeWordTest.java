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
        huffmanTree.readFile("WordAlliteration.txt");	
        huffmanTree.huffmanDecode();
    }
    
    // Long alliteration example
    public static void testTwo() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordAlliterationLong.txt");
        huffmanTree.huffmanDecode();
    }
    
    // One word repeated many times
    public static void testThree() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("SingleWordRepeat.txt");
        huffmanTree.huffmanDecode();
    }
    
    // Empty file (code check)
    public static void testFour() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("TreeWordEmptyFile.txt");
        huffmanTree.huffmanDecode();
    }
    
    // No repeating words
    public static void testFive() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("TreeWordNoRepeats.txt");
        huffmanTree.huffmanDecode();
    }
    
    // Many repeating words 
    public static void testSix() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("TreeWordManyRepeats.txt");
        huffmanTree.huffmanDecode();
    }

    // Random characters and symbols
    public static void testSeven() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordTreeSymbols.txt");
        huffmanTree.huffmanDecode(); 
    }
    
    // Mix of letters, numbers and symbols
    public static void testEight() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordTreeSymbols.txt");
        huffmanTree.huffmanDecode();  
    }
}
