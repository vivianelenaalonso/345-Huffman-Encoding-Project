
public class HuffmanArrayWord {

    public void HuffmanArrayWord(String inputFileName, int cmd) {
        if (cmd == 0) {
            encode(inputFileName, inputFileName + ".huf");
        } else if (cmd == 1) {
            decode(inputFileName, inputFileName + ".dec");
        } else {
            System.out.println("Invalid command");
        }
    }

    public void encode(String inputFileName, String outputFileName) {
        String array[] = new String[1000];
        
    }

    public void decode(String inputFileName, String outputFileName) {

    }

    public void printFrequencyTable() {

    }


}
