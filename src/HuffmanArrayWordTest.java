public class HuffmanArrayWordTest {

    public static void main(String[] args) {

        HuffmanArrayWord huffman = new HuffmanArrayWord();
        huffman.encode("src\\testHffArrWrdTest.txt", "src\\huf_testHffArrWrdTest.txt");
        // huffman.printWordMap();  
        // huffman.printPQ();
        huffman.printEncodeMap();
        /*
        HuffmanArrayWord beeMovie;
        beeMovie = new HuffmanArrayWord("testBeeMovie.txt", 0);
        beeMovie.printEncodeMap();
        */
        HuffmanArrayWord macbeth = new HuffmanArrayWord();
        macbeth.encode("src\\testMacbeth.txt", "src\\huf_testMacbeth.txt");
        macbeth.printEncodeMap();
        
    }
}
