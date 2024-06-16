public class Sheep extends LiveStock{
    private int woolProduction;

    Sheep(String name, int healthRating,int woolProduction){//sheep constructor
        super("Sheep",name, healthRating, 5, "hay",50);
        this.woolProduction = woolProduction;
    }

    public String toString(){//sheep toString
        return super.toString() +
                "\n     She will produce " + woolProduction + " lbs of wool a year.\n";
    }
}