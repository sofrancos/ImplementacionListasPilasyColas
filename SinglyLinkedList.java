public class SinglyLinkedList {

private Node head;
private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    public void pushFront(int data) {
        Node nuevo = new Node (data);
        if (isEmpty ()){
            head = nuevo;
        }else{
            nuevo.next = head;
            head = nuevo;
        }
            size++;
    }

    public void pushBack(int data) {

        Node nuevo = new Node (data);
        if (isEmpty ()){
            head = nuevo;
        }else{
            Node current = head;

            while(current.next != null){
                current = current.next;
            }
            current.next = nuevo;
        }
        size++;
    }
    
    public int popFront() {
        if (isEmpty ()) return -1;

        Node out = head;
        head = head.next;
        out.next = null;

        size--;
        return out.data; 
    }

    public int popBack() {

        if(isEmpty())return -1;

        if (head.next == null) { 
            int data = head.data;
            head = null;
            size--;
            return data;
         }
        Node current = head;
        
        while(current.next.next != null){
            current = current.next;
        }
        
        int data = current.next.data;
        
        current.next = null;
        size--;
        return data; 
    }

    public Node find(int data) {

        Node current = head;

        while(current != null){
            if(current.data == data){
                return current;
            }
            current = current.next; 
        }
        return null; 
    }

    public void erase(Node node) {
        if(isEmpty()) return;
        if(head == node) {
            head = head.next;
            size--;
            return;
        }
        Node current = head;
        while(current.next != null && current.next != node){
            current = current.next;
        }
        if(current.next == null) return;
        current.next = current.next.next;
        size--;
    }

    public void addBefore(Node node, int data) {

        if(isEmpty()) return;
        Node nuevo = new Node(data);

        if(head == node){
            nuevo.next = head;
            head = nuevo;
            size++;
            return;
        }

        Node current = head;
        while(current.next != null && current.next != node){
            current = current.next;
        }

        if(current.next == null) return;
        nuevo.next = current.next;
        current.next = nuevo;
        size++;
    }

    public void addAfter(Node node, int data) {
        if(isEmpty()|| node== null) return;

        Node nuevo = new Node(data);
        nuevo.next = node.next;
        node.next = nuevo;
        size++;
    }
    public int topFront(){
        if(isEmpty()) return -1;
        return head.data;
    }

    public int topBack(){
        if(isEmpty()) return -1;
        Node current = head;
        while(current.next != null){
            current = current.next;
        }
        return current.data;
    }


    public boolean isEmpty() { 
        return head == null; 
    }

    public int size() {
        return size; 
    }
}
