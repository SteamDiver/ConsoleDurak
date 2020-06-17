package main.java.models.Card;

public class Card {
    private CardValue value;
    private CardSuit suit;

    public Card(CardValue value, CardSuit suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public String toString() {
        return String.format("|%s %s|", value.getLabel(), suit.label);
    }


    public CardSuit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }


    public boolean canBeat(Card card, CardSuit trumpSuit) {
        if (suit == trumpSuit) {
            if (card.getSuit() == trumpSuit)
                return value.getValue() > card.getValue().getValue();
            else
                return true;
        } else if (suit == card.getSuit())
                return value.getValue() > card.getValue().getValue();
        else
            return false;
    }
}
