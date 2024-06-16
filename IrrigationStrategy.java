import java.util.Scanner;

abstract class IrrigationStrategy {
    WeatherSensor weatherSens = new WeatherSensor();
    SoilMoistureSensor soilSens = new SoilMoistureSensor();
    String [] frequencyList = {"Twice a day", "Daily", "Every other day", "Every three days",
            "Every four days", "Every five days", "Every six days", "Weekly"};
    double irrigationAmount;
    double weatherFactor;
    double moistureFactor;
    int decisionScore;

    public void determineIrrigationAmount(double moistureLevel, String weatherCondition) {
        if (weatherCondition.equals("Sunny")) {
            weatherFactor = 1.5;
        } else if (weatherCondition.equals("Mostly Sunny")) {
            weatherFactor = 1.4;
        } else if (weatherCondition.equals("Partly Cloudy")) {
            weatherFactor = 1.3;
        } else if (weatherCondition.equals("Mostly Cloudy")) {
            weatherFactor = 1.2;
        } else if (weatherCondition.equals("Cloudy")) {
            weatherFactor = 1.1;
        } else if (weatherCondition.equals("Light Rain")) {
            weatherFactor = 0.8;
        } else if (weatherCondition.equals("Rain")) {
            weatherFactor = 0.5;
        } else if (weatherCondition.equals("Heavy Rain")) {
            weatherFactor = 0;
        }

        if (moistureLevel > 65) {
            moistureFactor = 0;
        } else if (moistureLevel > 60) {
            moistureFactor = 0.5;
        } else if (moistureLevel > 52) {
            moistureFactor = 0.8;
        } else if (moistureLevel > 44) {
            moistureFactor = 1.1;
        } else if (moistureLevel > 36) {
            moistureFactor = 1.2;
        } else if (moistureLevel > 28) {
            moistureFactor = 1.35;
        } else if (moistureLevel > 20) {
            moistureFactor = 1.5;
        } else moistureFactor = 1.5;
    }

    public void scheduleIrrigation() {

    }

    //Menu method. Separate bodies are written in each child class
    public abstract void irrigationStrategyMenu(double moistureLevel, String weatherCondition);
}
