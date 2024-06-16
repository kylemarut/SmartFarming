public class CarbonDioxide implements CarbonFootprint{
    //emitted primarily by farm equipment
    private int emissions = 0;
    private int reduction = 0;
    String[] reductionArr = {"Optimized plowing path", "Got a more energy-efficient tractor", "Installed solar panel to provide clean power"};
    public void trackEmissions(){//tracks emissions based on created crops
        for(int i = 0; i < Crop.cropArr.size(); i ++){
            if (Crop.cropArr.get(i).getCropType().equals("Tomato")){
                emissions += 100; //100 liters added for each tomato crop
            }
            else if (Crop.cropArr.get(i).getCropType().equals("Potato")){
                emissions += 200; //200 liters added for each potato crop
            }
            else emissions += 300; //300 liters added for each wheat crop
        }
        emissions -= reduction;//subtracts the reduction from calculation
        if (emissions < 0)
            emissions = 0;
    }
    public void reduceEmissions(){
        //decreases emissions and provides explanation
        System.out.println(reductionArr[(int)(Math.random()*reductionArr.length)]);
        emissions -= 100;
        reduction += 100;
        //without variable all reductions will be reset with the next trackEmissions() call
        if (emissions < 0)
            emissions = 0;
    }

    //getter
    public int getEmissions() {
        return emissions;
    }
}
