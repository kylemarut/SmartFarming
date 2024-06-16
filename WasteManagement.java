import java.util.Scanner;

public class WasteManagement {
    private static String[] wasteEx = {"Used manure as fertilizer", "Sold excess manure to neighboring farm",
            "Cleaned out shed feathers"};//different ways to manage waste
    private static String[] recycleEx = {"Composted crop residue", "Used crop clippings as mulch",
            "Took old containers to recycling center"};//different ways to manage recyclables
    private static double waste;//amount of accumulated waste
    private static double wasteRed;//amount waste has been reduced
    private static int recyclable;//amount of accumulated recyclables
    private static int recycleRed;//amount recyclables have been reduced

    public static void calculateWaste(){
        //uses amount of food animals eat to determine the amount of accumulated waste
        waste = 0;
        for (int i = 0; i < LiveStock.arr.size(); i++){
            waste += LiveStock.arr.get(i).getFoodQuantity();
        }
        waste -= wasteRed;//subtracts the reduction
        if (waste < 0)
            waste = 0;
    }
    public static void calculateRecyclable(){
        //uses the crop yield to determine accumulated recyclables
        recyclable = 0;
        for (int i = 0; i < Crop.cropArr.size(); i++){
            recyclable += Crop.cropArr.get(i).getCropYield()*5;
        }
        recyclable -= recycleRed;//subtracts the reduction
        if (recyclable < 0)
            recyclable = 0;
    }
    public static void manageWaste(){
        //lowers the waste in increases the reduction. This way when waste is next calculated the previous reductions
        //will still be in place
        waste -=20;
        wasteRed += 20;
        if (waste < 0)
            waste = 0;
        //selects an option from list to explain how waste was reduced
        System.out.println(wasteEx[(int)(Math.random()*wasteEx.length)]);

    }
    public static void recycleMaterials(){//same as manageWaste but for recyclables
        recyclable -=20;
        recycleRed += 20;
        if (recyclable < 0)
            recyclable = 0;
        System.out.println(recycleEx[(int)(Math.random()*recycleEx.length)]);
    }

    //getters
    public static double getWaste() {
        return waste;
    }
    public static int getRecyclable(){
        return recyclable;
    }

    public static void menu(){//menu specific for waste management

        System.out.println("\n----------------------");
        System.out.println("   Waste Management   ");
        System.out.println("----------------------\n");

        Scanner input = new Scanner(System.in);
        int val;
        loop:
        while(true) {
            System.out.println("""
                    1. View Animal Waste
                    2. View Recyclable Material
                    3. Manage Animal Waste
                    4. Manage Recyclable Material
                    5. Back to previous menu""");
            System.out.print("\nPlease select an option: ");
            val = input.nextInt();
            switch(val){
                case 1://displays waste
                    System.out.println("Current animal waste is " + getWaste() + " lbs");
                    break;
                case 2://displays recyclables
                    System.out.println("Current recyclable material is " + getRecyclable() + " lbs");
                    break;
                case 3://reduces waste
                    manageWaste();
                    break;
                case 4://reduces recyclables
                    recycleMaterials();
                    break;
                case 5://returns to main menu
                    break loop;
            }
        }
    }
}
