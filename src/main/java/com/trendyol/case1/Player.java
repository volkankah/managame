package com.trendyol.case1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Player {

    private static final Logger logger = Logger.getLogger(Player.class.getName());

    Random random = new Random();

    private int health = Constant.getMaximumHealth();

    private int manaCapacity = 0;
    private int mana = 0;

    private List<Card> deck = Utility.cardList(0,0,1,1,2,2,2,3,3,3,3,4,4,4,5,5,6,6,7,8);
    private List<Card> hand = new ArrayList<Card>();

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    protected Card highestCard() {
        return hand.stream().filter(card -> card.getValue() <= mana).max(Comparator.naturalOrder()).orElse(new Card(0));
    }

    public void initHand() {
        for (int i = 0; i < Constant.getInitialHandSize(); i++) {
            refreshHand();
        }
    }

    public void refreshHand() {
        if (deck.size() == 0) {
            logger.info(this + " deck size is 0 <=Bleeding Out Rule violation=>");
            health--;
        }else if (hand.size() == Constant.getMaximumHandSize()){
            logger.info(this + " cannot get new Card  <=Overload Rule violation=>");
        }else{
            Card card = deck.get(random.nextInt(deck.size()));
            deck.remove(card);
            hand.add(card);
        }
    }

    public void refineManaCapacity() {
        if (manaCapacity < Constant.getMaximumManaSlots()) {
            manaCapacity++;
        }
    }

    public void refillMana() {
        mana = manaCapacity;
    }

    private void receiveDamage(int damage) {
        health -= damage;
    }

    public boolean canPlay() {
        return hand.stream().filter(card -> card.getValue() <= mana ).count() > 0;
    }

    void playCard(Card card, Player opponent) {
        if (mana < card.getValue()) {
            logger.info("Not enough Mana "+ mana );
        }
        mana -= card.getValue();
        hand.remove(card);
        opponent.receiveDamage(card.getValue());
    }

    @Override
    public String toString() {
        return "Player:" + name + "{" +
                "health=" + health +
                ", mana=" + mana + "/" + manaCapacity +
                ", hand=" + hand +
                ", deck=" + deck +
                '}';
    }

}