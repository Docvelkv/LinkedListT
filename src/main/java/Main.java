import java.util.Random;

public class Main {
    public static void main(String[] args) {
        LinkedListT<Integer> list = new LinkedListT<>();
        for(int i = 0; i < 10; i++){
            list.addSorted(new Random().nextInt(10));
        }
        list.print();
        System.out.println("\n" + list.getValue(0));
        list.reverse();
        System.out.println();
        list.print();
        System.out.println("\n" + list.getValue(0));
    }
}
