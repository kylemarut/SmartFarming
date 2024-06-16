public class Cattle extends LiveStock{
    private int milkProduction;

    Cattle(String name, int healthRating, int milkProduction){//cattle specific constructor
        super("Cow",name, healthRating, 24, "hay",750);
        this.milkProduction = milkProduction;
    }

    public String toString(){//cattle specific constructor
        return super.toString() +
                "\n     She will produce " + milkProduction + " gallons of milk a day.\n" ;
    }

}
