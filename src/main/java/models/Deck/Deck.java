package main.java.models.Deck;

import main.java.models.Card.Card;
import main.java.models.Card.CardSuit;
import main.java.models.Card.CardValue;
import main.java.models.CardsList.CardsList;

import java.util.Collections;

public class Deck {
    private final CardsList cardsList = new CardsList();
    public Deck() {
        for(CardSuit s: CardSuit.values()){
            for(CardValue v: CardValue.values()){
                cardsList.add(new Card(v, s));
            }
        }
    }

    public CardsList getCardsList() {
        return cardsList;
    }

    public void shuffle() {
        Collections.shuffle(cardsList);
    }

    public CardsList takeCards(int num) {
        CardsList takenCardsList = new CardsList();
        for (int i = 0; i < num; i++)
            takenCardsList.add(cardsList.remove(i));
        return takenCardsList;
    }

    public int getLength() {
        return cardsList.size();
    }

    public void clear() {
        cardsList.clear();
    }

}
