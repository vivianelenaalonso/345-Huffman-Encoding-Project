/*  File: MaxHeap.java
 *  Author: Stephen Pan
 *  Purpose: Max-heap implementation for Huffman encoding. Most of this code was taken from PriorityQueue.java and modified to fit a max-heap.
 */

import java.util.ArrayList;

public class MaxHeap {

    private ArrayList<Node> heap;

    //defualt constructor
    public MaxHeap() {
        heap = new ArrayList<>();

    }

    //add a node to the heap
    public void add(Node n){
        heap.add(n);
        int current = heap.size()-1;
        while (heap.get(current).frequency > heap.get(getParent(current)).frequency){
            swap(current,getParent(current));
            current = getParent(current);}

        }

    //check if the heap contains a node
    public boolean contains(Node n){
        return heap.contains(n);
    }

    //add a node to the heap, but with a string and frequency given
    public void add(String element, int frequency){
        Node n = new Node(frequency, element);
        heap.add(n);
        int current = heap.size()-1;
        while (heap.get(current).frequency > heap.get(getParent(current)).frequency){
            swap(current,getParent(current));
            current = getParent(current);}
    }

    //returns the largest frequency node
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
            if (rightChild(current) < heap.size() && heap.get(rightChild(current)).frequency > heap.get(leftChild(current)).frequency){
                smallerChild = rightChild(current);
            }
            if (heap.get(current).frequency > heap.get(smallerChild).frequency){
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


    //returns the parent of a node
    private int getParent(int index){
        return (index-1)/2;
    }

    //returns the left child of a node
    private int leftChild(int index){
        return index*2+1;
    }

    //returns the right child of a node
    private int rightChild(int index){
        return index*2+2;
    }

    //returns a string representation of the heap
    @Override
	public String toString(){
        String s = "";
        for (Node n: heap){
            s+=n.toString()+" ";
        }
        return s;
    }

    //checks if the heap is empty
    public boolean isEmpty(){
        return heap.size() == 0;
    }

    public Node peek() {
    	if (heap.isEmpty()) {
    		return null;
    	}
    	return heap.get(0);
    }

}
