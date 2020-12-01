import java.util.*;

public class Main2 {
    private int mod = 99997867;

    private void sln() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), D = sc.nextInt();
        long cnt = 0;
        if (N <= 2) {
            System.out.println(-1);
            return;
        }
//        int[] locs = new int[N];
//        for (int i = 0; i < N; i++) {
//            locs[i] = sc.nextInt();
//        }
        sc.close();
        int left = 0, right = 2;
        while (right < N) {
//            if (locs[right] - locs[left] > D) left++;
//            else if (right - left < 2) right++;
//            else {
                cnt += calC(right - left);
                right++;
//            } 
        }
        cnt %= mod;
        System.out.println(cnt);
    }

    private long calC(long num) {
        System.out.println(num + " *" +  (num - 1) + "/ 2");
        return num * (num - 1) / 2;
    }


    public static void main(String[] args) {
        new Main2().sln();
    }
}
