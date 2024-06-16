public class Potato extends Crop {

    Potato(String growthStage, int diseaseSusceptibility){//potato constructor
        super("Potato", growthStage, diseaseSusceptibility);
        cropYield = 10/diseaseSusceptibility;
    }

    public String toString() {//constructor toString method
        return super.toString() +
                "\n     The crop will yield " + cropYield + " potatoes.";
    }
}
