public class codeTesting {
    
    public static void main(String[] args) {
        
        PriorityQueue pq = new PriorityQueue();
        Node n2 = new Node(2,'b');
        Node n3 = new Node(3,'c');
        Node n4 = new Node(4,'d');
        Node n5 = new Node(5,'e');
        Node n1 = new Node(1,'a');
        pq.add(n3);
        pq.add(n4);
        pq.add(n5);
        pq.add(n2);
        pq.add(n1);
        System.out.println(pq.toString());
        pq.pop();
        System.out.println(pq.toString());
        pq.pop();
        System.out.println(pq.toString());





    }
}
