package com.finki;

public class GreedyCoins {

    void sortiraj_paricki(int coins[], int n) {
        int i, j, tmp;

        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if (coins[i] < coins[j]) {
                    tmp = coins[i];
                    coins[i] = coins[j];
                    coins[j] = tmp;
                }
            }
        }
    }

// coins e niza so vrednostite na parickite koi se dadeni
// n e brojot na paricki
// sum e dadenata suma
// coinsNum e niza za resenieto so brojot na paricki od sekoja golemina na paricka
    int greedyCoins(int coins[], int n, int sum, int coinsNum[]) {

        sortiraj_paricki(coins, n);

        int i = 0;
        int br = 0; // vkupniot broj na paricki za da se formira dadenata suma

        while (sum > 0) {
            coinsNum[i] = sum / coins[i];
            sum -= coinsNum[i] * coins[i];
            br += coinsNum[i];
            i++;
        }

        while (i < n) {
            coinsNum[i] = 0;
            i++;
        }

        return br;
    }

    void sortiraj(int p[], int t[], int n) {
        int i, j, tmpP, tmpT;

        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                if (((float) p[i] / (float) t[i]) < ((float) p[j] / (float) t[j])) {
                    tmpP = p[i];
                    tmpT = t[i];
                    p[i] = p[j];
                    t[i] = t[j];
                    p[j] = tmpP;
                    t[j] = tmpT;
                }
            }
        }

    }


    public static void main(String[] args) {

        GreedyCoins g = new GreedyCoins();

        int coins[] = new int[5];
        int n = 5;
        int sum = 79;
        int coinsNum[] = new int[5];

        coins[0] = 1;
        coins[1] = 2;
        coins[2] = 5;
        coins[3] = 10;
        coins[4] = 50;

        System.out.println("Greedy coins: " + g.greedyCoins(coins, n, sum, coinsNum));
        
    }
}
