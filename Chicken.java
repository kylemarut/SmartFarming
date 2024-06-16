public class Chicken extends LiveStock{
    private int eggProduction;

    Chicken(String name, int healthRating,int eggProduction){//chicken specific constructor
        super("Chicken",name, healthRating, .15, "corn", 5);
        this.eggProduction = eggProduction;
    }


    public String toString(){//chicken specific toString
        return super.toString() +
                "\n     She will produce " + eggProduction + " eggs a day.\n";
    }
}
