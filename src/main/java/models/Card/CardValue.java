package main.java.models.Card;

public enum CardValue {
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    J("Валет",11),
    Q("Королева",12),
    K("Король",13),
    A("Туз",14);

    private String label;
    private int value;


    private CardValue(String label, int value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public int getValue() {
        return value;
    }
}
