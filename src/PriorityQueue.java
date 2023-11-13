import java.util.ArrayList;

public class PriorityQueue {

    private ArrayList<Node> heap;

    public PriorityQueue() {
        heap = new ArrayList<Node>();

    }

    public void add(Node n){
        heap.add(n);
        int current = heap.size()-1;
        while (heap.get(current).frequency < heap.get(getParent(current)).frequency){
            swap(current,getParent(current));
            current = getParent(current);}

        }

    public void add(String element, int frequency){
        Node n = new Node(frequency,element);
        heap.add(n);
        int current = heap.size()-1;
        while (heap.get(current).frequency < heap.get(getParent(current)).frequency){
            swap(current,getParent(current));
            current = getParent(current);}
    }

    public Node pop(){
        Node popped = heap.get(0);
        heap.set(0,heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        sink();
        return popped;
    }

    public int size(){
        return heap.size();
    }

    private void sink(){
        //implement later
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

    private void swap(int pos1, int pos2){
        Node temp = heap.get(pos1);
        heap.set(pos1,heap.get(pos2));
        heap.set(pos2,temp);
    }

    

    private int getParent(int index){
        return (index-1)/2;
    }

    private int leftChild(int index){
        return index*2+1;
    }

    private int rightChild(int index){
        return index*2+2;
    }

    public String toString(){
        String s = "";
        for (Node n: heap){
            s+=n.character + " :  " + n.frequency+" ";
        }
        return s;
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
