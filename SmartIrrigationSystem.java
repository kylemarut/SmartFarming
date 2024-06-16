public class SmartIrrigationSystem{


    public static void monitorCropHealth(Crop obj) {//uses disease susceptibility to determine how healthy plant is
        if (obj.getDiseaseSusceptibility()<3)
            System.out.println("Perfect health\n");
        else if (obj.getDiseaseSusceptibility()<6)
            System.out.println("Healthy\n");
        else if (obj.getDiseaseSusceptibility()<9)
            System.out.println("Unhealthy\n");
        else System.out.println("Severely unhealthy\n");

    }

    public static void applyFertilizer(Crop cropObj, NitrousOxide NitrousObj) {
        //applies fertilizer to increase crop health. This also raiser nitrous oxide levels, however.
        System.out.println(cropObj.getCropType() + " given fertilizer\n");
        cropObj.setDiseaseSusceptibility(cropObj.getDiseaseSusceptibility()-1);
        NitrousObj.trackEmissions();
    }
}
