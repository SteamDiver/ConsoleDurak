package main.java.models.Player;

import main.java.models.Card.CardSuit;
import main.java.models.Card.CardValue;
import main.java.models.CardsList.CardsList;
import main.java.models.Card.Card;
import main.java.models.Player.Player;
import main.java.models.Table.Table;

import java.util.stream.Collectors;

public class AIPlayer extends Player {
    public AIPlayer(CardsList cardsList, String name){ super(cardsList, name); }

    @Override
    public int processing(Table table, Player mainPlayer, CardSuit trump) {
        CardsList mainTableCards = table.getPlayerCards(mainPlayer);
        CardsList aiTableCards = table.getPlayerCards(this);
        int mainTableCardsLength = mainTableCards.size();
        // int aiTableCardsLength = aiTableCards.size();

        int allTableCardsLength = table.getAllCardsLength();

        if (allTableCardsLength == 0) {
            table.addCard(this, hand.remove(getMinNonTrumpCard(hand, trump)));
            return 0;
        } else if (allTableCardsLength % 2 == 1)
        {
            Card mainCard = mainTableCards.get(mainTableCardsLength - 1);
            int beatCardIndex = getBeatCard(mainCard, trump);
            if (beatCardIndex != -1) {
                table.addCard(this, hand.remove(beatCardIndex));
                return 0;
            }
            else
                return 1;
        } else if (allTableCardsLength % 2 == 0) {
            int minExistsCardIndex = getMinExistsCard(table, trump);
            if (minExistsCardIndex != -1) {
                table.addCard(this, hand.remove(minExistsCardIndex));
                return 0;
            }
            else
                return 2;
        }
        return 1;
    }

    private int getMinExistsCard(Table table, CardSuit trump) {
        CardsList cardsExists = hand.stream().filter(table::existsCard).collect(Collectors.toCollection(CardsList::new));
        return cardsExists.size() != 0 ? getMinNonTrumpCard(cardsExists, trump): -1;
    }

    private int getBeatCard(Card mainCard, CardSuit trump) {
        CardsList canBeatCards = hand.stream().filter(card -> card.canBeat(mainCard, trump)).collect(Collectors.toCollection(CardsList::new));
        return canBeatCards.size() != 0 ? getMinNonTrumpCard(canBeatCards, trump): -1;
    }

    private int getMinNonTrumpCard(CardsList cards, CardSuit trump) {
        Card minCard = cards.get(0);
        CardSuit minCardSuitId = minCard.getSuit();
        CardValue minCardValue = minCard.getValue();

        for (Card card: cards) {
            CardValue cardValue = card.getValue();
            CardSuit cardSuit = card.getSuit();

            if (cardSuit != trump) {
                if (minCardValue.getValue() > cardValue.getValue() || minCardSuitId == trump) {
                    minCard = card;
                    minCardSuitId = minCard.getSuit();
                    minCardValue = minCard.getValue();
                }
            } else {
                if (minCardSuitId == trump && cardValue.getValue() < minCardValue.getValue()) {
                    minCard = card;
                    minCardSuitId = minCard.getSuit();
                    minCardValue = minCard.getValue();
                }
            }
        }
        return hand.indexOf(minCard);
    }
}
