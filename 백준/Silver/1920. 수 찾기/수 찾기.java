import java.util.*;
import java.io.*;
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        sc.nextLine();          // 🔥 개행 제거

        String a = sc.nextLine();

        int M = sc.nextInt();
        sc.nextLine();          // 🔥 개행 제거

        String b = sc.nextLine();

        String[] aStr = a.split(" ");
        int[] A = new int[aStr.length];
        for(int i = 0; i < aStr.length; i++) {
            A[i] = Integer.parseInt(aStr[i]);
        }
        Arrays.sort(A);
        
        String[] bStr = b.split(" ");
        int[] B = new int[bStr.length];
        for(int i = 0; i < bStr.length; i++) {
            B[i] = Integer.parseInt(bStr[i]);
        }
        
        for(int i = 0; i < B.length; i++) {
            if(Arrays.binarySearch(A, B[i]) < 0) System.out.println(0);
            else System.out.println(1);
        }
        
        sc.close();
        return;
    }
}