// Smart Farming program created by Kyle Marut and Michael Rodriguez.

import java.util.*;

public class MainMenu {
    //variable declarations
    static int basicOrAdvanced = 1;
    static int irrigationTime; // format is in military time (e.g., 8am is 800 and 4pm is 1600)
    static IrrigationDecisionCalculator IDC = new IrrigationDecisionCalculator();
    static boolean pestPresence = false;

    public static void main(String[] args) {
        //creates various necessary objects

        WeatherSensor weatherSens = new WeatherSensor();
        SoilMoistureSensor soilSens = new SoilMoistureSensor();
        BasicIrrigationStrategy basicIS = new BasicIrrigationStrategy();
        AdvancedIrrigationStrategy advancedIS = new AdvancedIrrigationStrategy();

        WaterOptimization waterOpt = new WaterOptimization();
        EnergyOptimization energyOpt = new EnergyOptimization();

        Methane methObj = new Methane();
        CarbonDioxide carbonObj = new CarbonDioxide();
        NitrousOxide nitrousObj = new NitrousOxide();

        Scanner input = new Scanner(System.in);
        int val = 0; // The value that the user will input. It must be between 1 and 11

        do {
            try {
                //various methods. Results are used to send messages
                carbonObj.trackEmissions();
                methObj.trackEmissions();
                WasteManagement.calculateWaste();
                WasteManagement.calculateRecyclable();

                System.out.println("\n-----------------------");
                System.out.println("       Main Menu       ");
                System.out.println("-----------------------");

                System.out.println("\nMessages: ");
                for (int i = 0; i < LiveStock.arr.size();i++){//sends a message if any animals are unhealthy
                    if(LiveStock.arr.get(i).getHealthRating() <= 6)
                        System.out.println("     " + LiveStock.arr.get(i).getName() + "'s health should be looked at");
                }
                for (int i = 0; i < Crop.cropArr.size();i++){//sends a message if any plants are unhealthy
                    if(Crop.cropArr.get(i).getDiseaseSusceptibility() >= 6)
                        System.out.println("     " + Crop.cropArr.get(i).getCropType() + "'s health should be looked at");
                }
                if (methObj.getEmissions() > 500){//sends a message if methane output is high
                    System.out.println("     Methane output should be looked at.");
                }
                if (nitrousObj.getEmissions() > 500){//sends a message if nitrous oxide output is high
                    System.out.println("     Nitrous oxide output should be looked at.");
                }
                if (carbonObj.getEmissions() > 500){//sends a message if carbon dioxide output is high
                    System.out.println("     Carbon dioxide output should be looked at.");
                }

                System.out.println
                        ("""
                        \n1. View Sensor Data
                        2. Configure Settings
                        3. Set Sensors
                        4. Generate Reports
                        5. Manage Livestock
                        6. Manage Crops
                        7. System Information
                        8. Resource Optimization
                        9. Waste Management
                        10. Carbon Footprint
                        11. Exit
                        """);

                System.out.print("Please select an option: ");

                val = input.nextInt();
                input.nextLine();

                switch(val) {
                    case 1:// Views sensor data
                        if (weatherSens.getWeatherCondition() == null || soilSens.getMoistureLevel() == 0) {
                            System.out.println("Please enter the weather condition and moisture level first.");
                            System.out.println("Do this by setting sensors (Main Menu, Option 3).");
                        } else {
                            System.out.println("\n---------------------");
                            System.out.println("     Sensor Data     ");
                            System.out.println("---------------------\n");

                            System.out.println("Current weather conditions : " + weatherSens.getWeatherCondition());
                            System.out.println("Current soil moisture level: " + soilSens.getMoistureLevel() + "%");
                        }
                        break;
                    case 2:// Configures settings
                        if (weatherSens.getWeatherCondition() == null || soilSens.getMoistureLevel() == 0) {
                            System.out.println("Please enter the weather condition and moisture level first.");
                            System.out.println("Do this by setting sensors (Main Menu, Option 3).");
                        } else {

                            // if these conditions are met, there's a 25% chance that pests will begin to show up.
                            if(weatherSens.getWeatherCondition().equals("Sunny") && soilSens.getMoistureLevel() > 60) {
                                int random = (int)(Math.random() * 4 + 1);

                                if (random == 1) {
                                    pestPresence = true;
                                }
                            }

                            if (basicOrAdvanced == 1) {
                                basicIS.irrigationStrategyMenu(soilSens.getMoistureLevel(),
                                        weatherSens.getWeatherCondition());
                            }
                            else if (basicOrAdvanced == 2) {
                                advancedIS.irrigationStrategyMenu(soilSens.getMoistureLevel(),
                                        weatherSens.getWeatherCondition());
                            }
                        }
                        break;
                    case 3:// Performs actions
                        SensorSet.Menu(weatherSens,soilSens);
                        break;
                    case 4:// Generates reports
                        if (weatherSens.getWeatherCondition() == null || soilSens.getMoistureLevel() == 0) {
                            System.out.println("Please enter the weather condition and moisture level first.");
                            System.out.println("Do this by setting sensors (Main Menu, Option 3).");
                        } else {
                            IDC.generatedReports(soilSens.getMoistureLevel());
                        }
                        break;
                    case 5:// Manages livestock
                        LiveStock.Menu();
                        break;
                    case 6:// Manages crops
                        Crop.Menu(nitrousObj);
                        break;
                    case 7:// Prints system information
                        SystemInfo.Menu();
                        break;
                    case 8:// Resource optimization
                        if (weatherSens.getWeatherCondition() == null || soilSens.getMoistureLevel() == 0) {
                            System.out.println("Please enter the weather condition and moisture level first.");
                            System.out.println("Do this by setting sensors (Main Menu, Option 3).");
                        } else {
                            waterOpt.optimizeWaterUsage(weatherSens,soilSens);
                            energyOpt.optimizeEnergyUsage(weatherSens);
                            waterOpt.displayWaterUsage();
                            energyOpt.displayEnergyUsage();
                        }
                        break;
                    case 9:// Waste management
                        WasteManagement.menu();
                        break;
                    case 10:// Carbon footprint information
                        EmissionsMenu.Menu(methObj,nitrousObj,carbonObj);
                        break;
                    case 11:// Exits the program
                        System.out.println("Program exited successfully.");
                        System.exit(0);
                        break;
                    default:
                        throw new InputMismatchException("");
                }
            } catch (InputMismatchException e) {
                System.out.println("\nInvalid response.");
            }
        } while (true); // The loop will repeat until the user selects option 11.
    }
}
