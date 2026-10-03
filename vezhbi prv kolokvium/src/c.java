import java.util.*;
public class c {
    static void sortirajOpagacki(int coins[], int n) {
        int i, j, tmp;

        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if (coins[i] > coins[j]) {
                    tmp = coins[i];
                    coins[i] = coins[j];
                    coins[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt(); //broj na zadachi
        int M = input.nextInt(); //broj na vraboteni
        int[] vremezadacha = new int[N];
        int vkupnovreme = 0;
        for (int i = 0; i < N; i++) {
            vremezadacha[i] = input.nextInt();
            vkupnovreme += vremezadacha[i];
        }

        sortirajOpagacki(vremezadacha, N);

        int denovi = 0;
        int index = 0;
        while(index < N) {
            denovi++;
            int []vraboteni = new int[M];
            for(int i = 0; i < M; i ++) {
                vraboteni[i] = 8;
            }

            for(int i = 0; i < M && index < N;) {
                if(vraboteni[i] >= vremezadacha[index]) {
                    vraboteni[i] -= vremezadacha[index];
                    index++;
                } else {
                    i++;
                }
            }
        }

        int vremeslobodno = M * 8 * denovi - vkupnovreme;

        System.out.println(denovi + " " + vremeslobodno);
    }
}