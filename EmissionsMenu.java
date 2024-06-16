import java.util.Scanner;

public class EmissionsMenu {
    public static void Menu(Methane methObj, NitrousOxide nitrousObj, CarbonDioxide carbonObj){
        outerLoop:
        while(true) {
            System.out.println
                    ("""
                            \n---------------------
                                Emission Menu
                            ---------------------
                            \n1. Check Emissions
                            2. Decrease Emissions
                            3. Back to previous menu
                            """);
            Scanner input = new Scanner(System.in);
            System.out.print("Please select an option: ");
            int valOut = input.nextInt();
            int valIn;
            innerLoop:
            while (true) {
                switch (valOut) {
                    case 1://checks individual emissions
                        System.out.println("""
                                Which greenhouse gas do you wish to check?
                                1. Methane
                                2. Nitrous Oxide
                                3. Carbon Dioxide
                                4. Previous Menu
                                """);
                        System.out.print("Please select an option: ");
                        valIn = input.nextInt();
                        switch (valIn){
                            case 1://methane emissions
                                System.out.println("The farm is currently emitting " + methObj.getEmissions() +
                                        " liters of methane a week.");
                                break;
                            case 2://nitrous oxide emissions
                                System.out.println("The farm is currently emitting " + nitrousObj.getEmissions() +
                                        " liters of nitrous oxide a week.");
                                break;
                            case 3://carbon dioxide emissions
                                System.out.println("The farm is currently emitting " + carbonObj.getEmissions() +
                                        " liters of carbon dioxide a week.");
                                break;
                            case 4://returns to initial emissions menu
                                break innerLoop;
                            default:
                                System.out.println("Invalid response.");
                                break;
                        }
                        break;
                    case 2://decrease individual emissions
                        System.out.println("""
                                Which greenhouse gas do you wish to decrease?
                                1. Methane
                                2. Nitrous Oxide
                                3. Carbon Dioxide
                                4. Previous Menu
                                """);
                        System.out.print("Please select an option: ");
                        valIn = input.nextInt();
                        switch (valIn){
                            case 1://methane reduction
                                methObj.reduceEmissions();
                                break;
                            case 2://nitrous oxide reduction
                                nitrousObj.reduceEmissions();
                                break;
                            case 3://carbon dioxide reduction
                                carbonObj.reduceEmissions();
                                break;
                            case 4://returns to initial emissions menu
                                break innerLoop;
                            default:
                                System.out.println("Invalid response.");
                                break;
                        }
                        break;
                    case 3://returns to main menu
                        break outerLoop;
                    default:
                        System.out.println("\nInvalid response.");
                        break innerLoop;
                }
            }
        }
    }
}
