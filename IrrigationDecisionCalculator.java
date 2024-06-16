public class IrrigationDecisionCalculator {

    private double energyUsage; // calculated in kWh
    private int waterUsage; // calculated in gallons
    private int irrigationRate; // calculated in gallons per minute
    private int irrigationDuration;

    public double getEnergyUsage() {
        return energyUsage;
    }

    public int getWaterUsage() {
        return waterUsage;
    }

    public int getIrrigationDuration() {
        return irrigationDuration;
    }

    // This method assumes that 2 inches of water per week equals to 60 gallons per day,
    // being applied at a rate of 1 gallon / minute.

    // This method also assumes that you will only have one irrigation session per day,
    // to prevent water-logging.
    public void calculator(double moisture) {
        int desiredMoistureLevel = 50, waterDeficit;
        int powerConsumptionIE = 2, operationDurationIE;

        waterDeficit = desiredMoistureLevel - (int) moisture;

        irrigationRate = 1;

        irrigationDuration = waterDeficit / irrigationRate;

        waterUsage = irrigationDuration * irrigationRate;

        // The maximum amount of irrigation time provided is sixty minutes, so the
        // duration of irrigation equipment operation will always be 60.

        operationDurationIE = waterUsage;

        energyUsage = powerConsumptionIE * operationDurationIE;
    }

    public void recommendation(double moisture) {
        calculator(moisture);

        if (irrigationDuration < 20) {
            irrigationDuration = 20;
        }

        System.out.println("Irrigation duration: " + irrigationDuration + " minutes per session.");
    }

    public void generatedReports(double moisture) {
        calculator(moisture);

        System.out.println("\n-----------------------------");
        System.out.println("      Generated Reports      ");
        System.out.println("-----------------------------\n");

        System.out.println("Water usage: " + waterUsage + " gallons per day.");
        System.out.println("Energy usage: " + energyUsage + " kWh per day.");
    }
}
