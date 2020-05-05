package com.trendyol.case1;

import java.util.Random;
import java.util.logging.Logger;

public class Game {

    private static final Logger logger = Logger.getLogger(Game.class.getName());

    private Player activePlayer;
    private Player opponentPlayer;

    Game(Player player1, Player player2) {

        Random random = new Random();

        activePlayer = random.nextBoolean() ? player1 : player2;
        if (activePlayer == player1) {
            opponentPlayer = player2;
        } else {
            opponentPlayer = player1;
        }
        activePlayer.initHand();
        opponentPlayer.initHand();
    }

    private void beginTurn() {
        activePlayer.refineManaCapacity();
        activePlayer.refillMana();
        activePlayer.refreshHand();
        logger.info( "beforeTurn => \n activePlayer: "+activePlayer +" \n opponentPlayer :"+opponentPlayer);
    }

    void switchPlayer() {
        logger.info( "afterTurn => \n activePlayer: "+activePlayer +" \n opponentPlayer :"+opponentPlayer);
        Player previouslyActivePlayer = activePlayer;
        activePlayer = opponentPlayer;
        opponentPlayer = previouslyActivePlayer;
    }

    private Player getWinner() {
        if (activePlayer.getHealth() < 1) {
            return opponentPlayer;
        } else if (opponentPlayer.getHealth() < 1) {
            return activePlayer;
        } else {
            return null;
        }
    }

    private void playTurn() {
        beginTurn();
        while (activePlayer.canPlay()) {
            activePlayer.playCard(activePlayer.highestCard(),opponentPlayer);
        }
        switchPlayer();
    }

    public Player playAll() {
        while (true) {
            if (getWinner() == null) {
                playTurn();
            } else {
                logger.info(getWinner() + " wins ..");
                return getWinner();
            }
        }
    }

    public static void main(String... args) {
        new Game(new Player("Player1"), new Player("Player2")).playAll();
    }

}