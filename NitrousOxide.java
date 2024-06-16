public class NitrousOxide implements CarbonFootprint{
    //Nitrous Oxide is emitted from soils, especially fertilizers
    private int emissions = 0;
    String[] reductionArr = {"Employed nitrification inhibitors", "Reduced waterlogging after last rain",
            "Optimized fertilizer use" };
    public void trackEmissions(){
        emissions += 100; //called whenever plants are fertilized
    }
    public void reduceEmissions(){
        //reduces emissions and selects explanation from array
        System.out.println(reductionArr[(int)(Math.random()*reductionArr.length)]);
        emissions -= 100;
        if (emissions < 0)
            emissions = 0;
    }

    //getter
    public int getEmissions() {
        return emissions;
    }


}
