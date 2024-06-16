import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class LiveStock {
    static ArrayList<LiveStock> arr = new ArrayList<>();//ArrayList to hold all created animals
    private String type;//represents the type of animal
    private String name;//animal's name
    private int healthRating;//animal's health
    private double foodQuantity;//amount of food animal eats
    private String foodType;//type of food animal eats
    double meatProduction;//amount of meat the animal is expected to produce
    static DecimalFormat df = new DecimalFormat("#.###");

    LiveStock(String type, String name, int healthRating, double foodQuantity, String foodType,double meatProduction){
        //constructor
        this.type = type;
        this.name = name;
        this.healthRating = healthRating;
        this.foodQuantity = foodQuantity;
        this.foodType = foodType;
        this.meatProduction = meatProduction;
    }

    public String toString(){
        //toString method
        return type + ": "+ name +
                "\n     " + name + " eats " + df.format(foodQuantity) + " lbs of " + foodType + " daily." +
                "\n     "+
                name + " is projected to produce " + meatProduction + " lbs of meat.";
    }

    //getters and setters
    public String getType() {
        return type;
    }

    public String getName(){
        return name;
    }
    public int getHealthRating(){
        return healthRating;
    }
    public void setHealthRating(int i){
        healthRating = i;
    }
    public String getFoodType(){
        return foodType;
    }

    public double getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(double d){
        foodQuantity = d;
    }

    public double getMeatProduction() {
        return meatProduction;
    }

    public void setMeatProduction(double meatProduction) {
        this.meatProduction = meatProduction;
    }


    //menu method specific to LiveStock options
    public static void Menu(){
        outerLoop:
        while(true) {
            System.out.println
                    ("""
                            \n----------------------
                                Livestock Menu
                            ----------------------
                            \n1. Add Animal
                            2. Display Animals
                            3. Display Animal Health
                            4. Administer Medication
                            5. Manage Animal Feeding Schedule
                            6. Back to previous menu
                            """);
            Scanner input = new Scanner(System.in);
            System.out.print("Please select an option: ");
            int valOut = input.nextInt();
            int valIn;
            innerLoop:
            while (true) {
                switch (valOut) {
                    //case 1 allows you to add different animals
                    case 1:
                        System.out.println("What kind of Animal do you want to add?");
                        System.out.println
                                ("""
                                        \n1. Cattle
                                        2. Chicken
                                        3. Sheep
                                        4. Back to previous menu
                                        """);

                        System.out.print("Please select an option: ");
                        valIn = input.nextInt();
                        input.nextLine();
                        switch (valIn) {
                            //mini menu for each individual animal
                            case 1:
                                System.out.println(("Enter name, health rating, and milk production"));
                                arr.add(new Cattle(input.nextLine(),input.nextInt(), input.nextInt()));
                                break;
                            case 2:
                                System.out.println(("Enter name, health rating, and egg production"));
                                arr.add(new Chicken(input.nextLine(),input.nextInt(), input.nextInt()));
                                break;
                            case 3:
                                System.out.println(("Enter name, health rating, and wool production"));
                                arr.add(new Sheep(input.nextLine(),input.nextInt(), input.nextInt()));
                                break;
                            case 4: break innerLoop;//goes back to beginning of the livestock menu
                        }
                        break;
                    case 2: //case 2 calls the toString method for the selected animal
                        if (arr.isEmpty()) {
                            System.out.println("\nYou currently don't have any animals.\n");
                            break innerLoop;
                        } else {
                            System.out.println("Which animal do you want to display?");
                            for (int i = 0; i < arr.size(); i++) {
                                System.out.println(i + 1 + ". " + arr.get(i).getName());
                            }
                        }
                        System.out.println((arr.size() + 1 + ". Back to previous menu"));
                        System.out.print("\nPlease select an option: ");
                        valIn = input.nextInt();
                        if (valIn == arr.size() + 1){
                            break innerLoop;
                        }
                        else if (valIn <= arr.size()) {
                            System.out.println(arr.get(valIn - 1));
                        } else System.out.println("Invalid response.");
                        break;
                    case 3://case 3 calls uses the LivestockHealthMonitor class to determine animal health
                        if (arr.isEmpty()) {
                            System.out.println("\nYou currently don't have any animals.\n");
                            break innerLoop;
                        } else {
                            System.out.println("Which animal do you want to display?");
                            for (int i = 0; i < arr.size(); i++) {
                                System.out.println(i + 1 + ". " + arr.get(i).getName());
                            }
                        }
                        System.out.println((arr.size() + 1 + ". Back to previous menu"));
                        System.out.print("\nPlease select an option: ");
                        valIn = input.nextInt();
                        if (valIn == arr.size() + 1){
                            break innerLoop;
                        }
                        else if (valIn <= arr.size()) {
                            LivestockHealthMonitor.monitorHealth(arr.get(valIn - 1));
                        } else System.out.println("Invalid response");
                        break;
                    case 4://case 4 uses LivestockHealthMonitor class to improve animal health
                        if (arr.isEmpty()) {
                            System.out.println("\nYou currently don't have any animals.\n");
                            break innerLoop;
                        } else {
                            System.out.println("Which animal do you want to medicate?");
                            for (int i = 0; i < arr.size(); i++) {
                                System.out.println(i + 1 + ". " + arr.get(i).getName());
                            }
                        }
                        System.out.println((arr.size() + 1 + ". Back to previous menu"));
                        System.out.print("\nPlease select an option: ");
                        valIn = input.nextInt();
                        if (valIn == arr.size() + 1){
                            break innerLoop;
                        }
                        else if (valIn <= arr.size()) {
                            LivestockHealthMonitor.administerMedication(arr.get(valIn - 1));
                        } else System.out.println("Invalid");
                        break;
                    case 5:
                        //case 5 uses LivestockProductionManager to display and change how much the selected animal eats
                        if (arr.isEmpty()) {
                            System.out.println("\nYou currently don't have any animals.\n");
                            break innerLoop;
                        } else {
                            System.out.println("Which animal do you want to manage?");
                            for (int i = 0; i < arr.size(); i++) {
                                System.out.println(i + 1 + ". " + arr.get(i).getName());
                            }
                        }
                        System.out.println((arr.size() + 1 + ". Back to previous menu"));
                        System.out.print("\nPlease select an option: ");
                        valIn = input.nextInt();
                        if (valIn == arr.size() + 1){
                            break innerLoop;
                        }
                        else if (valIn <= arr.size()) {
                            LivestockProductionManager.optimizeFeedingSchedule(arr.get(valIn - 1));
                        } else System.out.println("Invalid");
                        break;
                    case 6:
                        break outerLoop;//breaks out of Livestock menu and goes back to the main menu
                    default:
                        System.out.println("Invalid response");
                }
            }
        }
    }
}
