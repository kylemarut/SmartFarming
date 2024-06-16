public class WaterOptimization implements ResourceOptimization{
    double rain;
    public void optimizeWaterUsage(WeatherSensor weatherSens, SoilMoistureSensor soilSens){
        String weather = weatherSens.getWeatherCondition();
        switch (weather){
            case "Sunny":
                rain = 0;
                break;
            case "Mostly Sunny":
                rain = 14.2;
                break;
            case "Partly Cloudy":
                rain = 28.5;
                break;
            case "Mostly Cloudy":
                rain = 42.8;
                break;
            case "Cloudy":
                rain = 57.1;
                break;
            case "Light Rain":
                rain = 71.4;
                break;
            case "Rain":
                rain = 85.7;
                break;
            case "Heavy Rain":
                rain = 100;
                break;
        }
        rain = rain * (100-soilSens.getMoistureLevel())/100;
        //does calculation to determine how much of current water is from precipitation
    }
    public void optimizeEnergyUsage(WeatherSensor weatherSens){

    }

    public void displayWaterUsage(){//displays how much water is from precipitation
        System.out.println(rain + "% of farm water is precipitation. \n" +
                (100-rain) + "% of farm power is traditional plumbing.");
    }
}
