import java.util.ArrayList;
import java.util.Scanner;

public class Crop {

    static ArrayList<Crop> cropArr = new ArrayList<>();//arrayList containing every crop object user creates
    private String cropType;
    private String growthStage;
    int cropYield;

    // diseaseSusceptibility is on a 1 to 10 scale, with 10 meaning that the crop is highly prone to disease.
    private int diseaseSusceptibility;

    Crop(String cropType, String growthStage, int diseaseSusceptibility) {//constructor
        this.cropType = cropType;
        this.growthStage = growthStage;
        this.diseaseSusceptibility = diseaseSusceptibility;
    }

    public String toString() {//toString
        return "Crop Type: " + cropType +
                "\n     " + "Growth Stage: " + growthStage +
                "\n     " + "Disease Susceptibility: " + diseaseSusceptibility;
    }

    //getters and setters
    public String getCropType(){
        return cropType;
    }
    public String getGrowthStage(){
        return growthStage;
    }
    public int getDiseaseSusceptibility(){
        return diseaseSusceptibility;
    }

    public void setDiseaseSusceptibility(int i) {
        diseaseSusceptibility = i;
    }

    public int getCropYield() {
        return cropYield;
    }

    public static void Menu(NitrousOxide nitrousObj) {//crop menu
        Scanner input = new Scanner(System.in);
        int valOut;
        int valIn;
        outerLoop:
        while(true) {
            System.out.println
                    ("""
                            \n-----------------------
                                   Crop Menu
                            -----------------------
                            \n1. Add Crop
                            2. Display Crops
                            3. Monitor Crop Health
                            4. Give Fertilizer
                            5. Back to previous menu
                            """);

            System.out.print("Please select an option: ");
            valOut = input.nextInt();
            innerLoop:
            while (true) {
                switch (valOut) {
                    case 1://allows user to add different crops
                        System.out.println("What kind of crop do you want to plant?");
                        System.out.println
                                ("""
                                        \n1. Potato
                                        2. Tomato
                                        3. Wheat
                                        4. Back to previous menu
                                        """);

                        System.out.print("Please select an option: ");
                        valIn = input.nextInt();
                        input.nextLine();
                        System.out.println(("Enter growth stage and disease" +
                                "\nsusceptibility on a 1-10 scale"));
                        switch (valIn) {
                            case 1://add potato
                                Crop.cropArr.add(new Potato(
                                        input.nextLine(), input.nextInt()));
                                break;
                            case 2://add tomato
                                Crop.cropArr.add(new Tomato(
                                        input.nextLine(), input.nextInt()));
                                break;
                            case 3://add wheat
                                Crop.cropArr.add(new Wheat(
                                        input.nextLine(), input.nextInt()));
                                break;
                            case 4: break innerLoop;//back to beginning of crop menu
                            default: System.out.println("Invalid response.\n");
                        }
                        break;
                    case 2://displays different created crops

                        if (cropArr.isEmpty()) {//checks to see if there are any crops
                            System.out.println("\nYou currently don't have any crops planted.");
                            break innerLoop;
                        } else {
                            System.out.println("\nWhich crop do you want to display?");
                            for (int i = 0; i < Crop.cropArr.size(); i++) {
                                System.out.println(i + 1 + ". " + Crop.cropArr.get(i).getCropType()
                                        + ", " +  Crop.cropArr.get(i).getGrowthStage());
                            }
                        }
                        System.out.println((Crop.cropArr.size() + 1 + ". Back to previous menu"));
                        System.out.print("\nPlease select an option: ");
                        valIn = input.nextInt();
                        if (valIn == Crop.cropArr.size() + 1){
                            break innerLoop;
                        }
                        else if (valIn <= Crop.cropArr.size()) {
                            System.out.println(Crop.cropArr.get(valIn - 1));//displays crop
                        } else System.out.println("Invalid response.");
                        // Configures settings
                        break;
                    case 3://monitor crop health
                        if (cropArr.isEmpty()) {
                            System.out.println("\nYou currently don't have any crops planted.");
                            break innerLoop;
                        } else {
                            System.out.println("Which plant do you want to display?");
                            for (int i = 0; i < cropArr.size(); i++) {
                                System.out.println(i + 1 + ". " + cropArr.get(i).getCropType());
                            }
                        }
                        System.out.println((cropArr.size() + 1 + ". Back to previous menu"));
                        System.out.print("\nPlease select an option: ");
                        valIn = input.nextInt();
                        if (valIn == cropArr.size() + 1){
                            break innerLoop;
                        }
                        else if (valIn <= cropArr.size()) {
                            //displays crop health using SmartIrrigationSystem
                            SmartIrrigationSystem.monitorCropHealth(cropArr.get(valIn - 1));
                        } else System.out.println("Invalid response");
                        break;
                    case 4://fertilizer
                        if (cropArr.isEmpty()) {
                            System.out.println("\nYou currently don't have any crops planted.");
                            break innerLoop;
                        } else {
                            System.out.println("Which plant do you want to fertilize?");
                            for (int i = 0; i < Crop.cropArr.size(); i++) {
                                System.out.println(i + 1 + ". " + Crop.cropArr.get(i).getCropType()
                                        + ", " +  Crop.cropArr.get(i).getGrowthStage());
                            }
                        }
                        System.out.println((cropArr.size() + 1 + ". Back to previous menu"));
                        System.out.print("\nPlease select an option: ");
                        valIn = input.nextInt();
                        if (valIn == cropArr.size() + 1){
                            break innerLoop;
                        }
                        else if (valIn <= cropArr.size()) {
                            //applies fertilizer using SmartIrrigationSystem
                            SmartIrrigationSystem.applyFertilizer(cropArr.get(valIn - 1),nitrousObj);
                        } else System.out.println("Invalid response.");
                        break;
                    case 5://goes back to main menu
                        break outerLoop;
                    default:
                        System.out.println("\nInvalid response.");
                        break innerLoop;
                }
            }
        }
    }


}
