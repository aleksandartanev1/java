public class sumaparnineparni {
    public static void main (String[] args) {
        int[] numbers = { 2, -9 , 0 , 5 , 12 , -25, 22, 9, 8, 12};
        int sum = 0;
        Integer odd = 0;
        Integer even = 0;
        Double average;


        //access all elements using for loop
        for(int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
            if(numbers[i] % 2 == 0) {
                even++;
            } else odd++;
        }
        int arraylength = numbers.length;
        average = ((double)sum / (double)arraylength);
        System.out.println("Number of even numbers is " + even + "\n" + "The number of odd numbers is " + odd + "\n" + "The average is " + average);



    }
}
