import java.util.Random;

public class Main {

    public static void main(String[]args) {
        Random rand = new Random();
        for(int i = 0; i < 1000; i++) {
            int a = 5+rand.nextInt(10); //fra og med 5 til 5+10-1=14
            if(a==15) {
                System.out.println(a);
            }
            //System.out.println(a);
            //System.out.println(rand.nextInt(52)); //gir tall fra og med 0 til 52. altså ikke med 52.
        }

    }
}
