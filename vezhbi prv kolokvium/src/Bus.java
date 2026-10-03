import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); //broj na vozrasni
        int M = Integer.parseInt(br.readLine()); // broj na deca
        br.close();
        int ticketcost = 100;
        int mintickets = Math.max(0,M - N) * ticketcost + N * ticketcost ;
        int maxtickets = Math.max(0,M - 1) * 100 + 100 + (N - 1) * 100;


        System.out.println(mintickets);
        System.out.println(maxtickets);


    }

}
