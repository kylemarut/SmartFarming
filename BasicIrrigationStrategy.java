import java.text.DecimalFormat; // This is so that the irrigation amount only prints 3 numbers after the decimal.
import java.util.Scanner;

public class BasicIrrigationStrategy extends IrrigationStrategy implements DecisionMaker {

    public static String basicFrequency;
    public static int basicDuration; // measured in minutes

    public int [] durationList = {5, 10, 15, 20, 25, 30, 45, 60};

    // BasicIrrigationStrategy assumes that each crop requires 1.5 inches of water per week.
    public void determineIrrigationAmount(double moistureLevel, String weatherCondition) {
        super.determineIrrigationAmount(moistureLevel,weatherCondition);
        if (weatherFactor == 0) { // if there's moderate to heavy rain, there's not much need to water.
            irrigationAmount = 0;
        } else if (weatherFactor == 0.5) {
            irrigationAmount = 0.5;
        } else irrigationAmount = ((weatherFactor + moistureFactor) / 2);

        DecimalFormat df = new DecimalFormat("#.###");
        String formattedIrrigationAmount = df.format(irrigationAmount);

        System.out.println("Current Settings:");
        System.out.println("\n\s\sFrequency: " + basicFrequency);
        System.out.println("\s\sDuration: " + basicDuration + " minutes");

        System.out.println("\nRecommended irrigation amount: " + formattedIrrigationAmount + " inches of water.");
    }

    public void scheduleIrrigation() {
        Scanner input = new Scanner(System.in);
        int valIn;

        System.out.println("Update Settings:");
        System.out.println("\nUpdate Frequency: ");
        for (int i = 0; i < frequencyList.length; i++) {
            System.out.println((i + 1) + ". " + frequencyList[i]);
        }

        System.out.print("\nPlease select an option: ");
        valIn = input.nextInt();
        if (valIn >= 1 && valIn <= 8) {
            basicFrequency = frequencyList[valIn - 1];
        } else {
            System.out.println("\nERROR: Invalid response.");
        }

        System.out.println("\nUpdate Duration: ");
        for (int i = 0; i < durationList.length; i++) {
            System.out.println((i + 1) + ". " + durationList[i] + " minutes");
        }

        System.out.print("\nPlease select an option: ");
        valIn = input.nextInt();
        if (valIn >= 1 && valIn <= 8) {
            basicDuration = durationList[valIn - 1];
        } else {
            System.out.println("\nERROR: Invalid response.");
        }
        System.out.println("Irrigation Scheduling Preferences Updated.");
        System.out.println("\nCurrent Settings:");
        System.out.println("\n\s\sFrequency: " + basicFrequency);
        System.out.println("\s\sDuration: " + basicDuration);
    }

    @Override
    public void makeIrrigationDecision(double moisture, String weather) {

        decisionScore = 0;

        if (weather.equals("Sunny") || weather.equals("Mostly Sunny")) { // mostly sunny or sunny
            decisionScore += 1;
        }

        if (moisture <= 30) { // 30% moisture level or lower
            decisionScore += 1;
        }

        System.out.print("Based on current conditions, ");
        if (decisionScore >= 1 || moisture < 50) {
            System.out.println("irrigation is recommended.");

            MainMenu.IDC.recommendation(moisture);
            System.out.println("");

            if (weather.equals("Sunny")) {
                System.out.println("Scheduled irrigation at 8:00 AM.");
                MainMenu.irrigationTime = 800;
            } else if (weather.equals("Mostly Sunny")) {
                System.out.println("Scheduled irrigation at 9:00 AM.");
                MainMenu.irrigationTime = 900;
            } else {
                System.out.println("Scheduled irrigation at 10:00 AM.");
                MainMenu.irrigationTime = 1000;
            }

        } else {
            System.out.println("irrigation is not necessary.");
        }
    }

    @Override
    public void makeFertilizationDecision(double moisture, String weather) {
        if (weather.equals("Sunny") || weather.equals("Mostly Sunny")) {
            if (moisture > 60) {
                System.out.println("It is recommended that you apply fertilizer to your plants often.");
                System.out.println("The reason is due to the high likelihood of pest presence.");
            } else {
                System.out.println("\nBased on current conditions, it is not necessary to apply fertilizer.");
                System.out.println("However, be sure to apply fertilizer to any crop that appears unhealthy.");
            }
        }
    }

    @Override
    public void makePestControlDecision() {
        if (!MainMenu.pestPresence) {
            System.out.println("\nNo pests have been detected in your crops.");
        } else {
            System.out.println("\nPests have been detected in your crops.");
            MainMenu.pestPresence = false;
            System.out.println("Organic insecticides have been applied.");
        }
    }

    public void irrigationStrategyMenu(double moistureLevel, String weatherCondition) {

        outerLoop:
        while(true) {
            System.out.println
                    ("""
                            \n-------------------------------
                                   Configure Settings
                            -------------------------------
                            
                            Current Irrigation Mode: Basic
                            \n1. Schedule Irrigation
                            2. Display Current Irrigation Settings
                            3. Make Decision
                            4. Switch to Advanced Irrigation Mode 
                            5. Back to previous menu
                            """);
            Scanner input = new Scanner(System.in);
            System.out.print("Please select an option: ");
            int valOut = input.nextInt();
            int valIn;
            innerLoop:
            while (true) {
                switch (valOut) {
                    case 1:
                        scheduleIrrigation();
                        break innerLoop;
                    case 2:
                        determineIrrigationAmount(moistureLevel, weatherCondition);
                        break innerLoop;
                    case 3:
                        makeIrrigationDecision(moistureLevel, weatherCondition);
                        makeFertilizationDecision(moistureLevel, weatherCondition);
                        makePestControlDecision();
                        break innerLoop;
                    case 4:
                        MainMenu.basicOrAdvanced = 2;
                        System.out.println("Current mode updated. Returning to main menu...");
                        break outerLoop;
                    case 5:
                        break outerLoop;
                    default:
                        System.out.println("\nInvalid response.");
                        break innerLoop;
                }
            }
        }
    }
}
