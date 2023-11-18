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
    }

    public static void testOne() {
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();
        huffmanTree.readFile("WordExample.txt");
    	
    }
}
