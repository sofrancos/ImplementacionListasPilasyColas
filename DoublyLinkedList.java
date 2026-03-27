public class DoublyLinkedList {
    private Node head;
    private int size;

    public DoublyLinkedList () {
        head = null;
        size = 0;
    }

    public void pushFront(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        size++;
    }
    public void pushBack(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
        }
        size++;
    }
    public int popFront() { 
        if (head == null) {
            return -1; 
        }
        int data = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        size--;
        return data;
    }
    public int popBack() { 
        if (head == null) {
            return -1; 
        }
        if (head.next == null) {
            int data = head.data;
            head = null;
            size--;
            return data;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        int data = current.data;
        current.prev.next = null;
        size--;
        return data;
    }
    public Node find(int data) {
        Node current = head;
        while (current != null) {
            if (current.data == data) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
    public void erase(Node node) {
        if (node == null || head == null) return;
        if (node == head) {
            popFront();
            return;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        size--;
        
    }
    public void addBefore(Node node, int data) {
        if (node == null) return;
        if (node == head) {
            pushFront(data);
            return;
        }
        Node newNode = new Node(data);
        newNode.next = node;
        newNode.prev = node.prev;
        node.prev.next = newNode;
        node.prev = newNode;
        size++;
    }
    public void addAfter(Node node, int data) {
        if (node == null) return;
        Node newNode = new Node(data);
        newNode.prev = node;
        newNode.next = node.next;
        if (node.next != null) {
            node.next.prev = newNode;
        }
        node.next = newNode;
        size++;
    }
    public boolean isEmpty() { 
        return head == null;
    }
    public int topFront() { 
        if (head == null) {
            return -1; 
        }
        return head.data;
    }
    public int topBack() {
        if (head == null) {
            return -1; 
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.data;
    }
    public int size() {
         return size;
    }
}
