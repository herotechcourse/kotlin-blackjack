package blackjack.model

import blackjack.controller.Controller.Companion.BLACKJACK_SCORE

class FinalResult(val dealer: Player, players: List<Player>) {
    var win: List<Player> = listOf()
        private set
    var lose: List<Player> = listOf()
        private set
    var draw: List<Player> = listOf()
        private set

    init {
        if (dealer.score > BLACKJACK_SCORE) {
            lose = players.filter { it.score > BLACKJACK_SCORE }
            win = players - lose
            draw = emptyList()
        } else {
            win = players.filter { it.score <= BLACKJACK_SCORE && it.score > dealer.score }
            draw = players.filter { it.score == dealer.score }
            lose = players - win - draw
        }
    }
}
