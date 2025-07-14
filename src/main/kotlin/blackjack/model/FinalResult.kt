package blackjack.model

class FinalResult(val dealer: Player, players: List<Player>) {
    var win: List<Player> = listOf()
        private set
    var lose: List<Player> = listOf()
        private set
    var draw: List<Player> = listOf()
        private set

    init {
        if (dealer.isBusted()) {
            win = players.filter { !it.isBusted() }
            lose = players.filter { it.isBusted() }
            draw = emptyList()
        } else {
            win = players.filter { !it.isBusted() && it.score > dealer.score }
            lose = players.filter { it.isBusted() || it.score < dealer.score }
            draw = players.filter { !it.isBusted() && it.score == dealer.score }
        }
    }
}
