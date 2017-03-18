import java.util.Arrays;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        int[][] history ={{1,2,2,5}, {6,1,2,3}, {2,6,2,3}};
        PinGenerator p = new PinGenerator("Andy", new Date(), 2958123, 5123130, history);
        System.out.println(Arrays.toString(p.generate()));
    }
}
