import java.util.ArrayList;

public class App {
    private static boolean isDouble = false;
    private static int currentStyle = 8;
    private static Settings settings = new Settings();

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("------------------------------");
            System.out.println(String.format("Current style: %s",StyleAppendix.getStyles().get(currentStyle).getName()));
            if (isDouble) {
                System.out.println(String.format("Current playstyle: Double (%d)",settings.getDoubleGN()));
            } else {
                System.out.println(String.format("Current playstyle: Single (%d)",settings.getSingleGN()));
            }
            System.out.println("1. Calculate Hi-Speed");
            System.out.println("2. Calculate Towel");
            System.out.println("3. Calculate Max BPM");
            System.out.println("4. Toggle playstyle");
            System.out.println("5. Change style");
            System.out.println("6. Settings");
            System.out.println("0. Exit");
            switch (Library.intInput("")) {
                case 1:
                    calculateHiSpeed();
                    break;
                case 2:
                    calculateTowel();
                    break;
                case 3:
                    calculateMaxBPM();
                    break;
                case 4:
                    isDouble=!isDouble;
                    break;
                case 5:
                    currentStyle = styleChoose();
                    break;
                case 6:
                    settingsMenu();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    Library.scan.close();
                    return;
                default:
                    System.out.println("Incorrect input.");
                    break;
            } 

        }
        // System.out.println("------------------------------");
        // System.out.println("")

        // StyleAppendix.getDefault().get(0);
        // StyleAppendix.getStyles().get(0);
    }

    public static void calculateHiSpeed() {
        ArrayList<String> output = new ArrayList<>();
        boolean hilighted=false;
        double whiteNumber;
        int greenNumber;
        String multiplier;
        System.out.println("------------------------------");
        double bpm = Library.doubleInput("BPM: ");
        for (HiSpeed hispeed : StyleAppendix.getStyles().get(currentStyle).getAvailableHS()) {
            greenNumber = isDouble ? settings.getDoubleGN() : settings.getSingleGN();
            whiteNumber = Library.findWhiteNumber(bpm, hispeed.getSpeedMultiplier(), greenNumber);
            if (whiteNumber>=Math.max(50,StyleAppendix.getStyles().get(currentStyle).getScreenlimit(settings))) {
                multiplier = String.format("(x%.2f)",hispeed.getSpeedMultiplier());
                output.add(String.format("%-5s %-7s WN = %d",hispeed.getName(),multiplier,Library.displayWhiteNumber(whiteNumber)));
            } else {
                if (!hilighted) {
                    if (!output.isEmpty()) {
                        output.set(output.size()-1, output.get(output.size()-1) + "\t\t********");
                    }
                    hilighted=true;
                }
                if (settings.isDisplayOverLimit()) {
                    multiplier = String.format("(x%.2f)",hispeed.getSpeedMultiplier());
                    output.add(String.format("%-5s %-7s GN = %.2f",hispeed.getName(),multiplier,Math.round(Library.findGreenNumber(bpm, hispeed.getSpeedMultiplier(), StyleAppendix.getStyles().get(currentStyle).getScreenlimit(settings))*100d)/100d));
                }
            }
        }
        for (String line : output) {
            System.out.println(line);
        }
    }

    public static void calculateTowel() {
        ArrayList<String> output = new ArrayList<>();
        boolean hilighted=false;
        double whiteNumber;
        int greenNumber;
        String multiplier;
        System.out.println("------------------------------");
        double bpm = Library.doubleInput("BPM: ");
        for (HiSpeed hispeed : StyleAppendix.getStyles().get(currentStyle).getAvailableHS()) {
            greenNumber = isDouble ? settings.getDoubleGN() : settings.getSingleGN();
            whiteNumber = Library.findWhiteNumber(bpm, hispeed.getSpeedMultiplier(), greenNumber);
            if (whiteNumber>=StyleAppendix.getStyles().get(currentStyle).getScreenlimit(settings)) {
                multiplier = String.format("(x%.2f)",hispeed.getSpeedMultiplier());
                output.add(String.format("%-5s %-7s Towel = %.2f",hispeed.getName(),multiplier,Math.round(Library.findTowel(whiteNumber,StyleAppendix.getStyles().get(currentStyle).getScreenlimit(settings))*100d)/100d));
            } else {
                if (!hilighted) {
                    if (!output.isEmpty()) {
                        output.set(output.size()-1, output.get(output.size()-1) + "\t\t********");
                    }
                    hilighted=true;
                }
                if (settings.isDisplayOverLimit()) {
                    multiplier = String.format("(x%.2f)",hispeed.getSpeedMultiplier());
                    output.add(String.format("%-5s %-7s GN = %.2f",hispeed.getName(),multiplier,Math.round(Library.findGreenNumber(bpm, hispeed.getSpeedMultiplier(), StyleAppendix.getStyles().get(currentStyle).getScreenlimit(settings))*100d)/100d));
                }
            }
        }
        for (String line : output) {
            System.out.println(line);
        }
    }

    public static void calculateMaxBPM() {
        System.out.println("------------------------------");
        int greenNumber;
        String multiplier;
        for (HiSpeed hispeed : StyleAppendix.getStyles().get(currentStyle).getAvailableHS()) {
            greenNumber = isDouble ? settings.getDoubleGN() : settings.getSingleGN();
            multiplier = String.format("(x%.2f)",hispeed.getSpeedMultiplier());
            System.out.println(String.format("%-5s %-7s --> %.2f",hispeed.getName(),multiplier,Math.round(Library.findBPM(greenNumber, hispeed.getSpeedMultiplier(),StyleAppendix.getStyles().get(currentStyle).getScreenlimit(settings))*100d)/100d));
        }
    }

    public static int styleChoose() {
        int n;
        System.out.println("------------------------------");
        for (int i=1;i<=StyleAppendix.getStyles().size();i++) {
            System.out.println(String.format("%3s %s",i+".",StyleAppendix.getStyles().get(i-1).getName()));
        }
        while (true) {
            n=Library.intInput("");
            if (n>0 && n<=StyleAppendix.getStyles().size()) {
                return n-1;
            } else {
                System.out.println("Incorrect input");
            }
        }
    }

    public static void settingsMenu() {
        while (true) {
            System.out.println("------------------------------");
            System.out.println(String.format("1. Change Singles GN (%d)",settings.getSingleGN()));
            System.out.println(String.format("2. Change Doubles GN (%d)",settings.getDoubleGN()));
            System.out.println(String.format("3. Toggle displayOverlimit (%s)",settings.isDisplayOverLimit() ? "True" : "False"));
            System.out.println(String.format("4. Toggle ignoreScreenLimit (%s)",settings.isIgnoreScreenLimit() ? "True" : "False"));
            System.out.println("0. Return");
            switch (Library.intInput("")) {
                case 1:
                    settings.setSingleGN(Library.intInput("Singles GN: "));
                    break;
                case 2:
                    settings.setDoubleGN(Library.intInput("Doubles GN: "));
                    break;
                case 3:
                    settings.toggleDisplayOverLimit();
                    break;
                case 4:
                    settings.toggleIgnoreScreenLimit();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Incorrect input.");
                    break;
            }
        }
    }
}
