import java.util.ArrayList;

public class Methane implements CarbonFootprint{
    //methane is emitted by animal digestion
    private int emissions = 0;
    private int reduction = 0;
    String[] reductionArr = {"Used different feed additives.", "Employed anaerobic digesters.", "Covered manure storage."};
    public void trackEmissions(){//calculates emissions based on animal populations
        for(int i = 0; i < LiveStock.arr.size(); i ++){
            if (LiveStock.arr.get(i).getType().equals("Cow")){
                emissions += 200; //200 liters added for each cow
            }
            else if (LiveStock.arr.get(i).getType().equals("Sheep")){
                emissions += 30; // 30 liters added for each sheep
            }
        } //Note: Chicken outputs of methane are so small they are not counted here
        emissions -= reduction;
        if (emissions < 0)
            emissions = 0;
    }
    public void reduceEmissions(){
        //reduces emissions and selects explanation from array
        System.out.println(reductionArr[(int)(Math.random()*reductionArr.length)]);
        emissions -= 100;
        reduction += 100;
        if (emissions < 0)
            emissions = 0;
    }

    public int getEmissions() {
        return emissions;
    }
}
