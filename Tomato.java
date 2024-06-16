public class Tomato extends Crop {

    Tomato(String growthStage, int diseaseSusceptibility){//tomato constructor
        super("Tomato", growthStage, diseaseSusceptibility);
        cropYield = 50/diseaseSusceptibility;
    }

    public String toString() {//tomato toString
        return super.toString() +
                "\n     The crop will yield " + cropYield + " tomatoes.";
    }
}
