import java.lang.Math;
public class LivestockHealthMonitor {
    private static String[] medications = {"antibiotics", "vaccination", "vitamin injection", "worm medicine",
            "hydrogen peroxide", "crushed charcoal"};//different ways of medicating the animals
    public static void monitorHealth(LiveStock obj){//uses health rating to determine how healthy animal is
        if (obj.getHealthRating()>9)
                System.out.println("Perfect health\n");
        else if (obj.getHealthRating()>6)
            System.out.println("Healthy\n");
        else if (obj.getHealthRating()>3)
            System.out.println("Unhealthy\n");
        else System.out.println("Severely unhealthy\n");
    }
    public static void administerMedication(LiveStock obj){
        //increases health and gives explanation from list
        System.out.println(obj.getName() + " given " + medications[(int)(Math.random()*medications.length)] +"\n");
        obj.setHealthRating(obj.getHealthRating()+1);
    }
}
