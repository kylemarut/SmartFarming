public interface DecisionMaker {
    void makeIrrigationDecision(double moisture, String weather);
    void makeFertilizationDecision(double moisture, String weather);
    void makePestControlDecision();
}
