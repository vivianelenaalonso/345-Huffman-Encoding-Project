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
        // Input text
        String inputText = "Sally sells seashells by the seashore\n" +
                "She sells seashells on the seashell shore\n" +
                "The seashells she sells are seashore shells\n" +
                "Of that Im sure\n" +
                "She sells seashells by the seashore\n" +
                "She hopes she will sell all her seashells soon\n" +
                "If neither he sells seashells\n" +
                "Nor she sells seashells\n" +
                "Who shall sell seashells\n" +
                "Shall seashells be sold";

     // Create an instance of HuffmanTreeWord
        HuffmanTreeWord huffmanTree = new HuffmanTreeWord();

        // Initialize priority queue
       // huffmanTree.priorityQueue = new PriorityQueue();

        // Read the file and perform Huffman encoding
        huffmanTree.huffmanEncode(inputText);

        // Generate Huffman codes and build the Huffman tree
        huffmanTree.makeEncoding();

        // Print the encoded text
        huffmanTree.printEncode();

        // Perform Huffman decoding
        huffmanTree.huffmanDecode();

        // Print the decoded text
        huffmanTree.printDecode();
    }
}
