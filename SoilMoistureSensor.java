import java.util.Scanner;

public class SoilMoistureSensor implements Sensor {
    private int moistureLevel;
    public void readMoistureLevel() {//sets the moisture level as a percentage
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter the current moisture level (%): ");
        moistureLevel = input.nextInt();
    }

    public void readWeatherCondition() {

    }

    //getter
    public int getMoistureLevel() {
        return moistureLevel;
    }
}
