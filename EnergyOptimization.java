import java.text.DecimalFormat; // This is so that all percentages only print 3 numbers after the decimal.

public class EnergyOptimization implements ResourceOptimization{
    private double solar;
    public void optimizeWaterUsage(WeatherSensor weatherSens, SoilMoistureSensor soilSens){

    }
    public void optimizeEnergyUsage(WeatherSensor weatherSens){
        //calculates how much of farm power can be currently obtained from solar power considering the current weather
        String weather = weatherSens.getWeatherCondition();
        switch (weather){
            case "Sunny":
                solar = 100;
                break;
            case "Mostly Sunny":
                solar = 85.7;
                break;
            case "Partly Cloudy":
                solar = 71.4;
                break;
            case "Mostly Cloudy":
                solar = 57.1;
                break;
            case "Cloudy":
                solar = 42.8;
                break;
            case "Light Rain":
                solar = 28.5;
                break;
            case "Rain":
                solar = 14.2;
                break;
            case "Heavy Rain":
                solar = 0;
                break;
        }
    }
    public void displayEnergyUsage(){//displays how much of current power is solar vs traditional electricity

        DecimalFormat df = new DecimalFormat("#.###");
        String formattedEnergyUsage = df.format(100 - solar);

        System.out.println(solar + "% of farm power is solar. \n" +
                (formattedEnergyUsage) + "% of farm power is traditional electricity.");
    }
}
