/**
 * @author Vivian Elena Alonso
 * 
 * @purpose To test the Huffman Tree Class done by tree 
 * for words. 
 * 
 */
public class TreeWordTest {
    public static void main(String[] args) {
        testOne();
        testTwo();
        testThree();
        testFour();
    }

    public static void testOne() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordExample.txt");	
        huffmanTree.huffmanDecode();
    }
    
    public static void testTwo() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordExample2.txt");
        huffmanTree.huffmanDecode();
    }
    
    public static void testThree() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordExample3.txt");
        huffmanTree.huffmanDecode();
    }
    
    public static void testFour() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordExample4.txt");
        huffmanTree.huffmanDecode();
    }
    
    public static void testFive() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordExample4.txt");
        huffmanTree.huffmanDecode();
    }
    
    public static void testSix() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordExample4.txt");
        huffmanTree.huffmanDecode();
    }
    
    public static void testSeven() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordExample4.txt");
        huffmanTree.huffmanDecode();
    }
    
    public static void testEight() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordExample4.txt");
        huffmanTree.huffmanDecode();
    }
}
