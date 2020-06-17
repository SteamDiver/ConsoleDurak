package main.java.models.Player;

import main.java.models.Card.Card;
import main.java.models.Card.CardSuit;
import main.java.models.CardsList.CardsList;
import main.java.models.Table.Table;

public abstract class Player {
    private String playerName;
    protected CardsList hand;
    protected Player(CardsList cardsList, String name) {
        hand = cardsList;
        playerName = name;
    }

    public void addCards(CardsList cardsList) {
        hand.addAll(cardsList);
    }


    public void cardsPrinting() {
        System.out.print(playerName + " ");
        hand.print();
    }

    public int getHandLength() {
        return hand.size();
    }

    public abstract int processing(Table table, Player oppositePlayer, CardSuit trumpSuit);

    public Card getMinCard(CardSuit trumpSuit) {
        Card minCard = null;
        for (Card card: hand)
            if (card.getSuit() == trumpSuit){
                if (minCard == null)
                    minCard = card;
                else if (card.getValue().getValue() < minCard.getValue().getValue())
                    minCard = card;
            }
        return minCard;
    }

    public String getPlayerName() {
        return playerName;
    }
}
