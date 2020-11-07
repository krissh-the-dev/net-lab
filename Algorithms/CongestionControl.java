import java.util.*;

class Queue {
  int q[], f = 0, r = 0, size;

  void insert(int n) {
    Scanner in = new Scanner(System.in);
    q = new int[10];

    for (int i = 0; i < n; i++) {
      System.out.print("Enter " + i + " element: ");
      int ele = in.nextInt();
      if (r + 1 > 10) {
        System.out.println("\nQueue is full \nLost Packet: " + ele);
        break;
      } else {
        r++;
        q[i] = ele;
      }
    }
    in.close();
  }

  void delete() {
    if (r == 0)
      System.out.print("\nQueue empty ");
    else {
      for (int i = f; i < r; i++) {
        try {
          Thread.sleep(1000);
        } catch (Exception e) {
        }

        System.out.print("\nLeaked Packet: " + q[i]);
        f++;
      }
    }
    System.out.println();
  }
}

class CongestionControl extends Thread {
  public static void main(String ar[]) throws Exception {
    Queue q = new Queue();
    Scanner src = new Scanner(System.in);

    System.out.print("Enter the packets to be sent: ");
    int size = src.nextInt();

    src.close();
    q.insert(size);
    q.delete();
  }
}
