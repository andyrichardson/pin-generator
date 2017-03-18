import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

class PinGenerator {
    static final int length = 4;
    private static final Random rand = new Random();

    private String name, dob, sortcode, accountnumber;
    private int[] pin;
    private int[][] history;

    PinGenerator(String n, Date d, int s, int a, int[][] h){
        name = n;
        dob = d.toString();
        sortcode = Integer.toString(s);
        accountnumber = Integer.toString(a);
        history = h;

        pin = new int[PinGenerator.length];
    }

    int[] generate(){
        boolean valid;

        do{
            makePin();
            valid = runChecks();
        }while(!valid);

        return pin;
    }

    /* Validation */
    private boolean checkRepetitions(){
        int[] previous = {-1, -1};

        for(int n : pin){
            if(previous[0] == n && previous[1] == n){
                return false;
            }

            previous[0] = previous[1];
            previous[1] = n;
        }

        return true;
    }

    private boolean checkSequence(){
        String s = "0123456789";
        return !s.contains(pinToString());
    }

    private boolean checkReverseSequence(){
        String s = "9876543210";
        return !s.contains(pinToString());
    }

    private boolean checkHistory(){
        for(int[] p : history){
            if(Arrays.equals(p, pin)){
                return false;
            }
        }

        return true;
    }

    private boolean checkAccountNo(){
        return !accountnumber.contains(pinToString());
    }

    private boolean checkSortCode(){
        return !sortcode.contains(pinToString());
    }

    /* Utils */
    private void makePin(){
        for(int n = 0; n < PinGenerator.length; n++){
            pin[n] = rand.nextInt(9);
        }
    }

    private boolean runChecks(){
        boolean valid;

        valid = checkRepetitions();
        valid = valid & checkSequence();
        valid = valid & checkHistory();
        valid = valid & checkAccountNo();
        valid = valid & checkSortCode();
        valid = valid & checkReverseSequence();

        return valid;

    }

    private String pinToString(){
        return Arrays.toString(pin).replace("[", "").replace("]", "").replace(",", "").replace(" ", "");
    }
}
