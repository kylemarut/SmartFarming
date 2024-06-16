import java.text.DecimalFormat;
import java.util.Scanner;

public class LivestockProductionManager {
    static Scanner sc = new Scanner(System.in);
    public static int option;//int for which menu option user selects
    static DecimalFormat df = new DecimalFormat("#.###");

    public static void checkFeedingSchedule(LiveStock obj){//displays food schedule
        System.out.println(obj.getName() + " gets fed " + df.format(obj.getFoodQuantity()/3) + "lbs of " + obj.getFoodType() +
                " 3 times a day\n");

    }
    public static void increasingFeeding(LiveStock obj){ //increases food amount by 10%
        obj.setFoodQuantity(obj.getFoodQuantity()*1.1);
        obj.setMeatProduction(obj.getMeatProduction()*1.05);
    }

    public static void decreasingFeeding(LiveStock obj){ //decreases food amount by 10%
        obj.setFoodQuantity(obj.getFoodQuantity()*.9);
        obj.setMeatProduction(obj.getMeatProduction()*.95);
    }
    public static void optimizeFeedingSchedule(LiveStock obj){
        loop:
        while (true){
            System.out.println("Select an option?");
            System.out.println("""
                    1. Check Feeding Schedule
                    2. Increase Feeding
                    3. Decrease Feeding
                    4. Back to previous menu
                    """);

            System.out.print("Please select an option: ");
            option = sc.nextInt();
            switch (option) {
                case 1://displays feeding schedule
                    checkFeedingSchedule(obj);
                    break;
                case 2://increases amount of food eats
                    increasingFeeding(obj);
                    break;
                case 3://decreases amount of food animal eats
                    decreasingFeeding(obj);
                    break;
                case 4://goes back to Livestock menu
                    break loop;
                case 5:
                    System.out.println("Invalid");
                    break;
            }
        }
    }


}
