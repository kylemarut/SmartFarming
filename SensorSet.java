import java.util.Scanner;

public class SensorSet {
    public static void Menu( WeatherSensor weatherSens, SoilMoistureSensor soilSens){//sensor specific menu
        outerLoop:
        while(true) {
            System.out.println
                    ("""
                            -----------------------
                                  Set Sensors
                            -----------------------
                            \n1. Set Weather Sensor
                            2. Set Soil Moisture Sensor
                            3. Back to previous menu
                            """);
            Scanner input = new Scanner(System.in);
            System.out.print("Please select an option: ");
            int valOut = input.nextInt();
            innerLoop:
            while (true) {
                switch (valOut) {
                    case 1://sets weather
                        weatherSens.readWeatherCondition();
                        break innerLoop;
                    case 2:
                        soilSens.readMoistureLevel();
                        break innerLoop;
                    case 3:
                        break outerLoop;
                    default:
                        System.out.println("Invalid response");
                        break innerLoop;
                }
            }
        }
    }
}
