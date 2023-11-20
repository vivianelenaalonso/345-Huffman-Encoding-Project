import javax.swing.event.HyperlinkEvent;

public class HuffmanArrayWordTest {
    
    public static void main(String[] args) {
        
        HuffmanArrayWord huffman;
        huffman = new HuffmanArrayWord("src\\testHffArrWrdTest.txt", 0);
        // huffman.printWordMap();
        // huffman.printPQ();
        huffman.printEncodeMap();

        HuffmanArrayWord huffman2;
        huffman2 = new HuffmanArrayWord("src\\testHffArrWrdTest.txt.huf", 1);
        huffman2.printEncodeMap();

        
        HuffmanArrayWord beeMovie;
        beeMovie = new HuffmanArrayWord("src\\testBeeMovie.txt", 0);
        beeMovie.printEncodeMap();

        HuffmanArrayWord macbeth;
        macbeth = new HuffmanArrayWord("src\\testMacbeth.txt", 0);
        macbeth.printEncodeMap();
    }
}   
