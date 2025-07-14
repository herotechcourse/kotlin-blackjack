package blackjack.model

class FinalResult(dealer: Dealer, players: List<Player>) {
    var win: List<Player>
    var lose: List<Player>
    var draw: List<Player>

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

    companion object {
        private const val BLACKJACK_SCORE = 21
    }
}
