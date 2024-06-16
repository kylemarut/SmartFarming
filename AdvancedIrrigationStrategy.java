import java.text.DecimalFormat; // This is so that the irrigation amount only prints 3 numbers after the decimal.
import java.util.Scanner;

public class AdvancedIrrigationStrategy extends IrrigationStrategy implements DecisionMaker {

    private String formattedIrrigationAmount;

    private String advancedFrequency;
    private int frequencyIndex; // traverses through the frequencyList array to calculate the irrigation amount.
    private int advancedDuration; // measured in minutes
    private String selectedSoilType;
    private int topographyElevation;
    private String selectedBiome;


    public String [] soilType = {"Chalky", "Clay", "Loamy", "Peaty", "Sandy", "Silty"};
    public String [] biomes = {"Grassland", "Forest", "Desert", "Tundra", "Aquatic"};

    public void determineIrrigationAmount(double moistureLevel,
    String weatherCondition){//, double cropWaterRequirement) {
        double cropWaterRequirement = 0;
        for (int i = 0; i < Crop.cropArr.size(); i++) {
            String cropIndex = String.valueOf(Crop.cropArr.get(i));
            if (cropIndex.contains("Tomato")) {
                cropWaterRequirement = 1.5;
            } else if (cropIndex.contains("Potato") || cropIndex.contains("Wheat")) {
                cropWaterRequirement = 1.25;
            }
            super.determineIrrigationAmount(moistureLevel,weatherCondition);

            if (weatherFactor == 0) { // if there's moderate to heavy rain, there's not much need to water.
                irrigationAmount = 0;
            } else if (weatherFactor == 0.5) {
                irrigationAmount = 0.5;
            } else irrigationAmount = ((weatherFactor + moistureFactor + cropWaterRequirement) / 3);

            DecimalFormat df = new DecimalFormat("#.###");
            formattedIrrigationAmount = df.format(irrigationAmount);

            System.out.println("\nRecommended irrigation amount: " + formattedIrrigationAmount + " inches of water.");
        }
    }

    public void adjustIrrigationSchedule(String weather) {
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
    }

    public void considerSoilTypeAndTopography() {

        System.out.println("For best results, please determine your irrigation amount first " +
                "(Configure Settings Menu, Option 1)");
        Scanner input = new Scanner(System.in);
        int valIn;

        frequencyIndex = 0;

        System.out.println("\nSelect your soil type: ");
        for (int i = 0; i < soilType.length; i++) {
            System.out.println((i + 1) + ". " + soilType[i]);
        }

        System.out.print("\nPlease select an option: ");
        valIn = input.nextInt();
        if (valIn >= 1 && valIn <= 6) {
            selectedSoilType = soilType[valIn - 1];

            // The more water-absorbent a soil type is, the more it will decrease the irrigation amount.
            if (selectedSoilType.equals("Chalky")) {
                irrigationAmount += 0.3;
                frequencyIndex += 1;
                advancedDuration += 20;
            } else if (selectedSoilType.equals("Clay")) {
                irrigationAmount -= 0.4;
                frequencyIndex += 4;
                advancedDuration += 10;
            } else if (selectedSoilType.equals("Loamy")) {
                irrigationAmount -= 0.25;
                frequencyIndex += 3;
                advancedDuration += 15;
            } else if (selectedSoilType.equals("Peaty")) {
                irrigationAmount -= 0.3;
                frequencyIndex += 3;
                advancedDuration += 15;
            } else if (selectedSoilType.equals("Sandy")) {
                irrigationAmount += 0.5;
                frequencyIndex += 1;
                advancedDuration += 25;
            } else if (selectedSoilType.equals("Silty")) {
                irrigationAmount -= 0.15;
                frequencyIndex += 2;
                advancedDuration = 15;
            } else {
                System.out.println("\nERROR: Invalid response.");
            }
        } else {
            System.out.println("\nERROR: Invalid response.");
        }

        System.out.print("Enter your elevation (in ft): ");
        topographyElevation = input.nextInt();

        if (topographyElevation > 8000) {
            irrigationAmount += 0.02;
        } else if (topographyElevation > 6000) {
            irrigationAmount += 0.03;
        } else if (topographyElevation > 4000) {
            irrigationAmount += 0.04;
        } else if (topographyElevation > 2000) {
            irrigationAmount += 0.05;
        } else if (topographyElevation > 0) {
            irrigationAmount += 0.06;
        } else if (topographyElevation > -2000) {
            irrigationAmount += 0.07;
        } else if (topographyElevation > -4000) {
            irrigationAmount += 0.08;
        } else if (topographyElevation > -6000) {
            irrigationAmount += 0.09;
        } else irrigationAmount += 0.1;

        System.out.println("\nSelect your farming biome: ");
        for (int i = 0; i < biomes.length; i++) {
            System.out.println((i + 1) + ". " + biomes[i]);
        }

        System.out.print("\nPlease select an option: ");
        valIn = input.nextInt();
        if (valIn >= 1 && valIn <= 5) {
            selectedBiome = biomes[valIn - 1];

            if (selectedBiome.equals("Grassland")) {
                irrigationAmount += 0.2;
                frequencyIndex += 1;
                advancedDuration += 20;
            } else if (selectedBiome.equals("Forest")) {
                irrigationAmount += 0.1;
                frequencyIndex += 2;
                advancedDuration += 15;
            } else if (selectedBiome.equals("Desert")) {
                irrigationAmount += 0.5;
                frequencyIndex -= 1;
                advancedDuration += 25;
            } else if (selectedBiome.equals("Tundra")) {
                irrigationAmount -= 0.2;
                frequencyIndex += 4;
                advancedDuration += 15;
            } else if (selectedBiome.equals("Aquatic")) {
                irrigationAmount -= 0.4;
                frequencyIndex += 4;
                advancedDuration += 15;
            } else {
                System.out.println("\nERROR: Invalid response.");
            }
        }

        // Absolute minimums and maximums, so that crops don't dehydrate or get waterlogged.
        if (frequencyIndex < 3) {
            frequencyIndex = 3;
        } else if (frequencyIndex > 8) {
            frequencyIndex = 8;
        }

        if (advancedDuration > 60) {
            advancedDuration = 60;
        } else if (advancedDuration < 20) {
            advancedDuration = 20;
        }

        if (irrigationAmount > 2) {
            irrigationAmount = 2;
        } else if (irrigationAmount < 0.5) {
            irrigationAmount = 0.5;
        }

        advancedFrequency = frequencyList[frequencyIndex];

        System.out.println("Soil and topography information updated.");
    }

    public void currentSettings() {
        System.out.println("\n----------------------------");
        System.out.println("      Current Settings      ");
        System.out.println("----------------------------\n");
        System.out.println("Soil Type: " + selectedSoilType);
        System.out.println("Topography Elevation: " + topographyElevation);
        System.out.println("Biome: " + selectedBiome);
        System.out.println("Frequency: " + advancedFrequency);
        System.out.println("Duration: " + advancedDuration + " minutes");

        DecimalFormat df = new DecimalFormat("#.###");
        formattedIrrigationAmount = df.format(irrigationAmount);

        System.out.println("\nRecommended irrigation amount: " + formattedIrrigationAmount + " inches of water.");
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
            adjustIrrigationSchedule(weather);

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

    public void irrigationStrategyMenu(double moistureLevel, String weatherCondition){//, double cropWaterRequirement) {
        outerLoop:
        while(true) {
            System.out.println
                    ("""
                            \n-------------------------------
                                   Configure Settings
                            -------------------------------
                                                        
                            Current Irrigation Mode: Advanced
                            \n1. Determine Irrigation Amount
                            2. Automatically Adjust Irrigation Schedule
                            3. Update Soil Type and Topography
                            4. Display Current Settings
                            5. Make Decision
                            6. Switch to Basic Irrigation Mode
                            7. Back to previous menu
                            """);
            Scanner input = new Scanner(System.in);
            System.out.print("Please select an option: ");
            int valOut = input.nextInt();
            int valIn;
            innerLoop:
            while (true) {
                switch (valOut) {
                    case 1:
                        if (Crop.cropArr.isEmpty()) {
                            System.out.println("\nYou currently don't have any crops planted.");
                            System.out.println("Do this by adding crops (Main Menu, Option 6).");
                        } else determineIrrigationAmount(moistureLevel, weatherCondition);//,cropWaterRequirement);
                        break innerLoop;
                    case 2:
                        adjustIrrigationSchedule(weatherCondition);
                        break innerLoop;
                    case 3:
                        considerSoilTypeAndTopography();
                        break innerLoop;
                    case 4:
                        currentSettings();
                        break innerLoop;
                    case 5:
                        makeIrrigationDecision(moistureLevel, weatherCondition);
                        makeFertilizationDecision(moistureLevel, weatherCondition);
                        makePestControlDecision();
                        break innerLoop;
                    case 6:
                        MainMenu.basicOrAdvanced = 1;
                        System.out.println("Current mode updated. Returning to main menu...");
                        break outerLoop;
                    case 7:
                        break outerLoop;
                    default:
                        System.out.println("\nInvalid response.");
                        break innerLoop;
                }
            }
        }
    }
}
