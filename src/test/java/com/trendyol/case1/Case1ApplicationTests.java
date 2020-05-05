package com.trendyol.case1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Case1ApplicationTests {

	@Test
	void oneShouldWinOneShouldLose() {
	    Player player1 = new Player("Player1");
	    Player player2 = new Player("Player2");
        Player winner = new Game(player1, player2).playAll();
        Assertions.assertThat(winner.getHealth()).isGreaterThan(0);
        if(winner.equals(player1)){
            Assertions.assertThat(player2.getHealth()).isLessThanOrEqualTo(0);
        }else{
            Assertions.assertThat(player1.getHealth()).isLessThanOrEqualTo(0);
        }
	}
}
