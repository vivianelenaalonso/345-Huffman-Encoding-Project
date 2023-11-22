/*  File: PriorityQueue.java
 *  Author: Stephen Pan, Vivian Alonso
 *  Purpose: Min-heap implementation for Huffman encoding
 */

 import java.util.ArrayList;

public class PriorityQueue {

    private ArrayList<Node> heap;

    //default constructor
    public PriorityQueue() {
        heap = new ArrayList<>();

    }

    //add a node to the heap
    public void add(Node n){
        heap.add(n);
        int current = heap.size()-1;
        while (heap.get(current).frequency < heap.get(getParent(current)).frequency){
            swap(current,getParent(current));
            current = getParent(current);}

        }

    //add a node to the heap, but with a string and frequency given
    public void add(String element, int frequency){
        Node n = new Node(frequency, element);
        heap.add(n);
        int current = heap.size()-1;
        while (heap.get(current).frequency < heap.get(getParent(current)).frequency){
            swap(current,getParent(current));
            current = getParent(current);}
    }

    //returns the smallest frequency node
    public Node pop(){
        Node popped = heap.get(0);
        heap.set(0,heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        sink();
        return popped;
    }

    //returns the heap size.
    public int size(){
        return heap.size();
    }

    //sinks the node down the heap
    private void sink(){
        int current = 0;
        while (leftChild(current) < heap.size()){
            int smallerChild = leftChild(current);
            if (rightChild(current) < heap.size() && heap.get(rightChild(current)).frequency < heap.get(leftChild(current)).frequency){
                smallerChild = rightChild(current);
            }
            if (heap.get(current).frequency < heap.get(smallerChild).frequency){
                break;
            }
            else{
                swap(current,smallerChild);
            }
            current = smallerChild;
        }
    }

    //swaps two nodes
    private void swap(int pos1, int pos2){
        Node temp = heap.get(pos1);
        heap.set(pos1,heap.get(pos2));
        heap.set(pos2,temp);
    }

    //returns the parent of the node
    private int getParent(int index){
        return (index-1)/2;
    }

    //returns the left child of the node
    private int leftChild(int index){
        return index*2+1;
    }

    //returns the right child of the node
    private int rightChild(int index){
        return index*2+2;
    }

    //string method
    @Override
	public String toString(){
        String s = "";
        for (Node n: heap){
            s+=n.toString()+" ";
        }
        return s;
    }

    //returns true if the heap is empty
    public boolean isEmpty(){
        return heap.size() == 0;
    }

    /**
     * Peeks the first index of the heap and returns
     * the node.
     */
    public Node peek() {
    	if (heap.isEmpty()) {
    		return null;
    	}
    	return heap.get(0);
    }

}
