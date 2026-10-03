package com.finki;

public class GreedyKnapsack {

   
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

// p i t gi sodrzat profitot i tezinata na objektite
// C e kapacitet na paketot, x e vektor na resenieto
    float greedyFractionalKnapsack(int p[], int t[], float C, int n, float x[]) {

        sortiraj(p, t, n);
        // objektite se podredeni taka da bide zadovoleno p[i]/t[i] >= p[i+1]/t[i+1]

        int i;
        float profit = 0;

        for (i = 0; i < n; i++) {
            x[i] = 0;
        }

        for (i = 0; i < n; i++) {
            if (C > t[i]) {         // objektot go stavame celosno
                C -= t[i];
                profit += p[i];
            } else {                // objektot go stavame delumno
                profit += (C / (float) t[i]) * (float) p[i];
                C = 0;
                break;
            }
        }

        return profit;
    }

    public static void main(String[] args) {

        GreedyKnapsack g = new GreedyKnapsack();


        float C = 20;
        int n = 3;
        int p[] = new int[3];
        int t[] = new int[3];
        float x[] = new float[3];

        p[0] = 25;
        p[1] = 24;
        p[2] = 15;

        t[0] = 18;
        t[1] = 15;
        t[2] = 10;

        System.out.println("Greedy fractional knapsack: " + g.greedyFractionalKnapsack(p, t, C, n, x));
        
    }
}
