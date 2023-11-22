# 345-Huffman-Encoding-Project
Files: HuffmanTreeLetter.java
       HuffmanTreeWord.java
       HuffmanArrayLetter.java
       HuffmanArrayWord.java
       Node.java
       PriorityQueue.java
       MaxHeap.java
       HuffmanTreeLetterTest.java
       HuffmanArrayWordTest.java
       TreeWordTest.java
       HuffmanTest.java

Authors: Elodie Hilbert
         Vivian Alonso
         Kevin Li
         Stephen Pan

Purpose: The purpose of this program is to test Huffman Encoding with different structures and approaches. 
You can view the four main Huffman Encoding classes: HuffmanTreeLetter.java, HuffmanTreeWord.java, HuffmanArrayLetter.java,
HuffmanArrayWord.java and test them all by running HuffmanTest.java and providing text to encode or a encoded text file to 
decode. In addition you can test them individually, for example by running TreeWordTest.java for HuffmanTreeWord.java. 

1. Our classes work by first creating an Object of the class with no parameters as shown below.
       example: HuffmanTreeLetter test = new HuffmanTreeLetter();
2. Then you must call the encode method using test.encode(inputFile, outputFile) where "inputFile" is a string name of
   the file you would like to encode and "outputFile" is the string file you would like to write the encoded text to.
3. The encode method works by first going through the file and creating a frequency map using a HashMap.
4. Then using our priorityQueue class it uses the frequency map to create a priority queue of Nodes (using the Node class).
5. Then it builds a Tree (or Array) using these nodes.
6. Next it traverses the tree and creates a Map of character to encoded string and prints out these codes.
       example: e:00
                h:01
                o:10
                l:11
7. Lastly the encode class loops through the file, matching characters to their encoded string and printing this out while
   also writing this to a file.
       example: 0100111110
8. To decode, you would simply call test.decode() and using the previous tree and the encoding it will traverse to find
    the correct character based on the binary code, starting back at the root after a leaf node is found.
       example: hello

To test our code we have created a main HuffmanTest class which allows the user to choose which method to encode/decode 
with and what files they want to read from/write to on the console. In addition, we each have our own test class that 
tests our individual classes using many different text files. 

