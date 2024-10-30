import java.util.Scanner;

public class Library {

    static Scanner scan = new Scanner(System.in);

    public static int intInput(String guide) {
        while (true) {
            System.out.print(guide);
            String i = scan.nextLine();
            try {
                int r = Integer.parseInt(i);
                return r;
            } catch(NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public static double doubleInput(String guide) {
        while (true) {
            System.out.print(guide);
            String i = scan.nextLine();
            try {
                double r = Double.parseDouble(i);
                return r;
            } catch(NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public static double roundWhiteNumber(double whiteNumber) {
        double wn=50;
        while(true) {
            if (wn+(25/8)>=whiteNumber) {
                return wn;
            }
            else {
                wn+=25/8;
            }
        }
    }

    public static int displayWhiteNumber(double whiteNumber) {
        if (roundWhiteNumber(whiteNumber)==1000) {
            return 999;
        }
        return (int)Math.floor(roundWhiteNumber(whiteNumber));

    }

    public static double findWhiteNumber(double bpm, double speedMultiplier, double greenNumber) {
        return 1000-((bpm*speedMultiplier*greenNumber)/174);
    }

    public static double findGreenNumber(double bpm, double speedMultiplier, double whiteNumber) {
        System.out.println(bpm+" "+speedMultiplier+" "+whiteNumber);
        return 174000/(bpm*speedMultiplier*(1000/(1000-whiteNumber)));
    }

    public static double findTowel(double whiteNumber, int screenLimit) {
        return ((whiteNumber-screenLimit)/(1000-screenLimit))*1000;
    }

    public static double findBPM(int greenNumber,double speedMultiplier, int screenLimit) {
        return 174000/(greenNumber*speedMultiplier*(1000/(1000-screenLimit)));
    }


}
