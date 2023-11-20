public class HuffmanArrayWordTest {
    
    public static void main(String[] args) {
        
        HuffmanArrayWord huffman;
        huffman = new HuffmanArrayWord("testHffArrWrdTest.txt", 0);
        // huffman.printWordMap();
        // huffman.printPQ();
        huffman.printEncodeMap();

        HuffmanArrayWord huffman2;
        huffman2 = new HuffmanArrayWord("huf_testHffArrWrdTest.txt", 1);
        huffman2.printEncodeMap();
        
        HuffmanArrayWord beeMovie;
        // beeMovie = new HuffmanArrayWord("testBeeMovie.txt", 0);
        // beeMovie.printEncodeMap();

        HuffmanArrayWord macbeth;
        // macbeth = new HuffmanArrayWord("testMacbeth.txt", 0);
        // macbeth.printEncodeMap();
    }
}   
