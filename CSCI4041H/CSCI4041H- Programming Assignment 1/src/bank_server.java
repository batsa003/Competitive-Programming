import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Bat-Orgil on 10/28/2015.
 */
public class bank_server {

    public static void main(String[] args){
        // Initializes the priority queue with capacity of 1000 customers.
        Customer[] initialization = new Customer[1001];
        MaxPQ<Customer> pq = new MaxPQ<Customer>(initialization);

        //IO
        File f= new File("customer_info.txt");
        try {
            Scanner in = new Scanner(f);
            while (in.hasNext()) {
                String[] line1 = in.nextLine().split("\\s+");
                String[] line2 = in.nextLine().split("\\s+");
                String name = "";
                // It is assumed that name follows the format- Customer i: (NAME)
                for (int i = 2; i < line1.length; i++) {
                    name += line1[i];
                    if(i!=line1.length) name+=" ";
                }
                // It is assumed that priority follows the format- Customer i priority: (PRIORITY)
                int priority = Integer.parseInt(line2[3]);
                pq.insert(new Customer(name, priority));
            }
            System.out.println("Start of bank_server.java");
            System.out.println();
            System.out.println("Current customers: ");
            pq.display();
            System.out.println();
            System.out.println("Customer is getting extracted: " + pq.extract_max().toString());
            System.out.println("Customer is getting extracted: " + pq.extract_max().toString());
            Customer newcustomer = new Customer("Alan Turing", 100);
            Customer newcustomer2 = new Customer("Stephen Curry", 10000);
            System.out.println();
            System.out.println("New customer with following property is arrived: " + newcustomer.toString());
            pq.insert(newcustomer);
            System.out.println("New customer with following property is arrived: " + newcustomer2.toString());
            pq.insert(newcustomer2);
            System.out.println();
            System.out.println("Current priority queue is: ");
            pq.display();
            System.out.println();
            System.out.println("Customer is getting extracted: " + pq.extract_max().toString());
            System.out.println("Current priority queue is: ");
            pq.display();
            System.out.println();
            System.out.println("Customer with highest priority for now: " + pq.maximum());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}

// Customer class which has a name and a priority.
class Customer implements Comparable<Customer> {
    String name;
    int priority;

    public Customer(String n, int p){
        name=n;
        priority=p;
    }

    @Override
    public int compareTo(Customer o) {
        return priority-o.priority;
    }

    //Helper function for displaying the customer
    public String toString(){
        return ("Customer name: " + name + ", priority= " + priority);
    }

}

// Maximum Priority Queue is implemented.
class MaxPQ <T extends Comparable<T>> {
    // Contains the entries
    public T[] A;
    //Keeps track of size of the heap. Not necessarily the length of A.
    static int heap_size=0;

    // Generic constructor.
    public MaxPQ(T[] custs){
        // A = (T[])
        A= custs;
    }

    // Inserts an element to PQ
    void insert(T element){
        heap_size++;
        if(A.length<=heap_size){// Extend the array if A is full.
            T[] ar= (T[]) new Object[2*A.length];
            for(int i=0; i<A.length; i++){
                ar[i]= A[i];
            }
        }
        if(heap_size==0){
            A[1]=element;
            return;
        }
        A[heap_size]= element;
        increase_key(heap_size,element);
    }

    // Returns the top element of the heap
    T maximum(){
        if(heap_size==0) throw new NoSuchElementException("Heap is empty");
        return A[1];
    }

    // Extracts the top element from the heap, and builds a new heap.
    T extract_max(){
        if(heap_size<1){
            throw new NoSuchElementException("Heap underflow");
        }
        T max = A[1];
        A[1]=A[heap_size];
        heap_size--;
        max_heapify(1);
        return max;
    }


    // Keeps the heap property given that the left and right subtree at root i is a heap.
    void max_heapify(int i){
        int l =2*i;
        int r= 2*i+1;
        int largest=i;
        if(l<= heap_size && A[l].compareTo(A[i])>0){
            largest=l;
        }
        if(r<= heap_size && A[r].compareTo(A[largest])>0){
            largest=r;
        }

        if (largest!=i){
            T temp = A[i];
            A[i]=A[largest];
            A[largest]=temp;
            max_heapify(largest);
        }
    }


    // Increases the key at index i, and replaces with newkey. Make sure newkey is greated than A[i].
    void increase_key(int i, T newkey){
        if(newkey.compareTo(A[i])<0){
            throw new InvalidParameterException("New key is smaller than the current key");
        }
        A[i]=newkey;
        while (i>1 && A[i/2].compareTo(A[i])<0){
            // Swap A[i] with its parent A[i/2]
            T temp = A[i];
            A[i]=A[i/2];
            A[i/2]=temp;
            i= i/2;
        }
    }

    //Helper function which was used to display the elements in the heap
    void display(){
        for(int i=1; i<=heap_size; i++){
            System.out.println(A[i].toString());
        }
    }
}
