package solitaire;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.util.Scanner;

public class CircularLinkedList<E> {

    Node<E> head;
    Node<E> tail;
    int size;


    public CircularLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public int indexOf(Object obj) {
        int index = 0;
        Node<E> current = head;

        while (current != null) {
            if (current.item.equals(obj)) {
                return index;
            }
            index++;
            current = current.next;
        }

        return -1;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {  //O(1)
            throw new IndexOutOfBoundsException("Not a valid index :(");
        }
        return getNode(index).item;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }


    public boolean add(E item) {
        this.add(size, item);
        return true;

    }

    public void add(int index, E item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds!");
        }
        Node<E> temp = new Node<E>(item);
        if (size == 0) {
            head = temp;
            tail = temp;
            tail.next = temp;
        } else if (index == 0) {
            temp.next = head;
            head = temp;
            tail.next = head;
        } else if (index == size) {
            tail.next = temp;
            tail = temp;
            tail.next = head;
        } else {
            Node<E> before = this.getNode(index - 1);
            temp.next = before.next;
            before.next = temp;
        }
        size++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        E removed = null;
        if (size == 1) {
            removed = head.item;
            head = null;
            tail = null;
        } else if (index == 0) {
            removed = head.item;
            head = head.next;
            tail.next = head;
        } else if (index == size - 1) {
            Node<E> before = this.getNode(index - 1);
            removed = tail.item;
            tail = before;
            tail.next = head;
        } else {
            Node<E> before = this.getNode(index - 1);
            removed = before.next.item;
            before.next = before.next.next;
        }
        size--;
        return removed;
    }

    public String toString() {
        Node<E> current = head;
        StringBuilder result = new StringBuilder();
        if (size == 0) {
            return "";
        }
        if (size == 1) {
            return head.item.toString();

        } else {
            do {
                result.append(current.item);
                result.append(" ==> ");
                current = current.next;
            } while (current != head);
        }
        return result.toString();
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }

    }

    public void swap(int index) {       //swaps 27 with the number that comes after itself
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        if (index == 0) {
            Node<E> after = head.next;
            tail.next = head.next;
            head.next = head.next.next;
            tail.next.next = head;
            after.next = head;
            head = after;
        } else if (index == size - 1) {
            Node<E> before = this.getNode(index - 1);
            tail.next = head.next;
            head.next = before.next;
            before.next = head;
            Node<E> temp = head;
            head = tail;
            tail = temp;
        } else if (index == size - 2) {
            Node<E> temp = this.getNode(index);
            Node<E> before = this.getNode(index - 1);
            temp.next = tail.next;
            tail.next = temp;
            before.next = tail;
            tail.next = temp;
        } else {
            Node<E> temp = this.getNode(index);
            Node<E> before = this.getNode(index - 1);
            Node<E> after = this.getNode(index + 1);
            before.next = temp.next;
            temp.next = after.next;
            after.next = temp;
        }

    }

    public void move2Nodes(int index) {  //moves 28 two nodes down
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bound!");
        }
        if (index == 0) {
            Node<E> firstNode = this.getNode(index + 1);
            Node<E> secondNode = this.getNode(index + 2);
            Node<E> thirdNode = this.getNode(index + 3);
            head.next = thirdNode;
            firstNode.next = secondNode;
            secondNode.next = head;
            head = firstNode;
            tail.next = head;
        } else if (index == size - 1) {
            Node<E> firstNode = head.next;
            Node<E> before = this.getNode(index - 1);
            tail.next = firstNode.next;
            before.next = head;
            head.next = firstNode;
            firstNode.next = tail;
            tail = before;
        } else if (index == size - 2) {
            Node<E> temp = this.getNode(index);
            Node<E> before = this.getNode(index - 1);
            temp.next = head.next;
            head.next = temp;
            before.next = tail;
            tail.next = head;
        } else if (index == size - 3) {
            Node<E> temp = this.getNode(index);
            Node<E> before = this.getNode(index - 1);
            before.next = temp.next;
            tail.next = temp;
            tail = temp;
            tail.next = head;
        } else {
            Node<E> temp = this.getNode(index);
            Node<E> before = this.getNode(index - 1);
            Node<E> secondNode = this.getNode(index + 2);
            Node<E> thirdNode = this.getNode(index + 3);
            before.next = temp.next;
            secondNode.next = temp;
            temp.next = thirdNode;
        }
    }

    public void tripleCutIfLast(int topJoker, int botJoker) {
        Node<E> tj = this.getNode(topJoker);
        Node<E> bj = this.getNode(botJoker);
        Node<E> before = this.getNode(topJoker - 1);
        Node<E> after = this.head;
        if (topJoker == 26 && botJoker == 27) {
            tail = before;
            head = tj;
            tail.next = head;
            tj.next = bj;
            bj.next = after;
        }
    }

    public void tripleCutIfFirst(int topJoker, int botJoker) {
        Node<E> tj = this.getNode(topJoker);
        Node<E> bj = this.getNode(botJoker);
        Node<E> after = this.getNode(botJoker + 1);
        if (topJoker == 0 && botJoker == 1) {
            head = after;
            tail.next = tj;
            tj.next = bj;
            tail = bj;
            tail.next = head;

        }

    }

    public void tripleCut() {   // does the triple cut
        CircularLinkedList<E> tempTop = new CircularLinkedList<>();
        CircularLinkedList<E> tempMiddle = new CircularLinkedList<>();

        int topJoker = this.indexOf(28); //26
        int botJoker = this.indexOf(27); //27
        int temp = 0;

        if (botJoker < topJoker) {
            temp = topJoker;
            topJoker = botJoker;
            botJoker = temp;
        }

        int tj = (int) this.get(topJoker); // index 26 = 28
        int bj = (int) this.get(botJoker); // index 27 = 27


        Node<E> current = this.head;

        if ((botJoker == 27 && topJoker == 26)) {

            this.tripleCutIfLast(topJoker, botJoker);

        } else if ((botJoker == 0 && topJoker == 1)) {

            this.tripleCutIfFirst(topJoker, botJoker);
        } else {
            while (!current.item.equals(tj)) {
                tempTop.add(this.remove(0));
                current = this.head;
            }
            tempMiddle.add(this.remove(0));

            current = this.head;

            while (!current.item.equals(bj)) {
                tempMiddle.add(this.remove(0));
                current = this.head;
            }
            tempMiddle.add(this.remove(0));

            current = tempMiddle.head;
            while (current != null) {
                this.add(tempMiddle.remove(0));
                current = tempMiddle.head;
            }

            current = tempTop.head;
            while (current != null) {
                this.add(tempTop.remove(0));
                current = tempTop.head;
            }
        }
    }

    public void bottomCard() {   // finds the bottom card's number and moves that number of nodes from the start to before the last node
        CircularLinkedList<E> temp = new CircularLinkedList<>();
        int n = (int) this.tail.item;
        Node<E> current = this.head;
        int lastNum = (int) tail.item;
        int index = 0;
        while (index != lastNum) {
            temp.add(this.remove(0));
            current = this.head;
            index++;
        }
        int indexOfTail = indexOf(tail.item);
        temp.add(this.remove(indexOfTail));
        index++;
        while (index != 0) {
            this.add(temp.remove(0));
            index--;
        }

    }

    public int generateKeystream() {    // find the head's number and starting from head goes to numbers index and saves the number
        int n = (int) this.head.item;
        int index = 0;
        Node<E> current = this.head;
        while (index != n) {
            current = current.next;
            index++;
        }
        return (int) current.item;
    }

    public static void encrypt(CircularLinkedList<Integer> stringToNum, CircularLinkedList<Integer> keystreams, CircularLinkedList<Integer> encrypted) {
        int firstList;
        int secondList;
        int sum;
        for (int j = 0; j < keystreams.size; j++) {
            firstList = stringToNum.head.item;
            stringToNum.head = stringToNum.head.next;
            secondList = keystreams.head.item;
            keystreams.head = keystreams.head.next;

            sum = firstList + secondList;
            if (sum >= 26) {
                sum -= 26;
            }
            encrypted.add(sum);
        }
    }

    public static void decrypt(CircularLinkedList<Integer> encrypted, CircularLinkedList<Integer> keystreams, CircularLinkedList<Integer> decrypted, char[] arr, int xs) {
        int firstList;
        int secondList;
        int difference;

        for (int j = 0; j < keystreams.size; j++) {

            firstList = encrypted.head.item;
            encrypted.head = encrypted.head.next;

            secondList = keystreams.head.item;
            keystreams.head = keystreams.head.next;

            difference = firstList - secondList;

            if (difference < 0) {
                difference += 26;
            }
            decrypted.add(difference);
        }

        for (int i = 0; i < decrypted.size - xs; i++) {
            arr[i] = numToAlph(decrypted.head.item);
            decrypted.head = decrypted.head.next;
        }
    }

    public static int alphToNum(char a) {
        return a - 64;
    }

    public static char numToAlph(int i) {
        return (char) (i + 64);
    }

    public static void main(String[] args) throws FileNotFoundException {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();
        CircularLinkedList<Integer> stringToNum = new CircularLinkedList<>();
        CircularLinkedList<Integer> keystreams = new CircularLinkedList<>();
        CircularLinkedList<Integer> encrypted = new CircularLinkedList<>();
        CircularLinkedList<Integer> decrypted = new CircularLinkedList<>();

        File file = new File("C:\\Users\\igamb\\Documents\\num.txt");
        Scanner s = new Scanner(file);
        Scanner scan = new Scanner(System.in);
        int c;
        while (s.hasNext()) {
            c = s.nextInt();
            list.add(c);
        }

        String string;
        System.out.println("Please enter a sentence to encrypt");
        string = scan.nextLine();

        string = string.replaceAll("[^a-zA-Z]", "");
        string = string.toUpperCase();

        char[] decrytedString = new char[list.size];
        char[] array = string.toCharArray();

        int numOfChar = array.length;
        for (char cr : array) {
            stringToNum.add(alphToNum(cr));
        }
        int xs = numOfChar % 5;
        xs = 5 - xs;
        for (int i = 0; i < xs; i++) {
            stringToNum.add(24);
        }
        int nums = stringToNum.size;
        int count = 0;

        while (count != nums) {

            int j1 = list.indexOf(27);
            int j2 = list.indexOf(28);
            int temp;

            if (j1 < j2) {
                j1 = j1;
            } else {
                temp = j2;
                j2 = j1;
                j1 = temp;
            }

            int j27 = 0;
            if (j1 == list.indexOf(27)) {
                j27 = j1;
            } else if (j2 == list.indexOf(27)) {
                j27 = j2;
            }

            int j28 = 0;
            if (j1 == list.indexOf(28)) {
                j28 = j1;
            } else if (j2 == list.indexOf(28)) {
                j28 = j2;
            }
            list.swap(j27);
            list.move2Nodes(j28);
            list.tripleCut();
            list.bottomCard();
            keystreams.add(list.generateKeystream());
            count++;
        }
        encrypt(stringToNum, keystreams, encrypted);
        System.out.println("Encrypted code is " + encrypted);

        decrypt(encrypted, keystreams, decrypted, decrytedString, xs);
        System.out.print("After decrypting the code the secret sentence is ");
        System.out.println(decrytedString);
    }
}