// file name: Node.java
// authors: elodie hilbert, vivian alonso
// description: node class used for trees


public class Node {

	public int frequency;
	public Character character;
	public String word;
	public Node left;
	public Node right;

	// Node for Character based Huffman Tree
	public Node(int data, char c) {
		frequency = data;
		character = c;
		left = null;
		right = null;
	}

	/**
	 * Node for word-based Huffman Tree.
	 *
	 * @param w String of Node
	 * @param f Frequency of Node
	 */
	public Node(int f, String w) {
		frequency = f;
		word = w;
		left = null;
		right = null;
	}

	@Override
	public String toString() {
		return word + " : " + frequency;
	}

}
