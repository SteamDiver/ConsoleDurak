package main.java.models.Card;

public enum CardSuit {
    DIAMONDS("Бубны"),
    HEARTS("Червы"),
    PIKES("Пики"),
    CLOVERS("Трефы");

    String label;
    public String getLabel(){
        return label;
    }

    private CardSuit(String label) {
        this.label = label;
    }
}
