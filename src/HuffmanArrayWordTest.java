public class HuffmanArrayWordTest {
    
    public static void main(String[] args) {
        
        HuffmanArrayWord huffman;
        huffman = new HuffmanArrayWord("src\\hffArrWrdTest.txt", 0);
        huffman.printWordMap();
        huffman.printPQ();
        huffman.printEncodeMap();

        /*
        HuffmanArrayWord beeMovie;
        beeMovie = new HuffmanArrayWord("src\\beemovie.txt", 0);
        // beeMovie.printWordMap();

        HuffmanArrayWord macbeth;
        macbeth = new HuffmanArrayWord("src\\macbeth.txt", 0);
        */
    }
}   
