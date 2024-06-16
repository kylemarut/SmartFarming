public class Wheat extends Crop {


    Wheat(String growthStage, int diseaseSusceptibility){//constructor specific to wheat crops
        super("Wheat", growthStage, diseaseSusceptibility);
        cropYield = 20/diseaseSusceptibility;
    }

    public String toString() {//toString specific to wheat crops
        return super.toString() +
                "\n     The crop will yield " + cropYield + " bushels of wheat.";
    }
}
