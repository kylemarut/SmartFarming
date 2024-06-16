import java.util.Scanner;

public class WeatherSensor implements Sensor {
    private String weatherCondition;//string that holds the weather condition
    private String[] weatherOp = {"Sunny", "Mostly Sunny","Partly Cloudy", "Mostly Cloudy", "Cloudy","Light Rain",
    "Rain", "Heavy Rain"};//different options weather can be set to

    public void readMoistureLevel() {

    }

    public void readWeatherCondition() {//allows user to select current weather from list of options
        Scanner input = new Scanner(System.in);
        System.out.println("What is the current weather condition?");
        for (int i = 0; i < weatherOp.length; i++){
            System.out.println(i+1 + ". " + weatherOp[i]);
        }
        System.out.print("\nPlease select an option: ");
        int i = input.nextInt();
        if (i <= weatherOp.length){
            weatherCondition = weatherOp[i-1];
        }
        else System.out.println("Invalid");
    }
    public String getWeatherCondition(){
        return weatherCondition;
    }//getter
}
