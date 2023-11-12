
public class Node {

	public int frequency;
	public String character;
	public Node left;
	public Node right;
	
	public Node(int data, String c) {
		frequency = data;
		character = c;
		left = null;
		right = null;
	}
	
}
