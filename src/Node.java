
public class Node {

	public int frequency;
	public Character character;
	public String word;
	public Node left;
	public Node right;
	
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
	public Node(String w, int f) {
		frequency = f;
		word = w;
		left = null;
		right = null;
	}
	
}
