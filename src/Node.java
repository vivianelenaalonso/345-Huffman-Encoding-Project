
public class Node {

	public int frequency;
	public char character;
	public Node left;
	public Node right;
	
	public Node(int data, char c) {
		frequency = data;
		character = c;
		left = null;
		right = null;
	}
	
}
