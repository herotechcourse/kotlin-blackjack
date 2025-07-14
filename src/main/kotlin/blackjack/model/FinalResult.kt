package blackjack.model

class FinalResult(val dealer: Dealer, players: List<Gambler>) {
    var lose = players.filter { it.isBusted() }
    var draw: List<Gambler>
    var win: List<Gambler>

    init {
        val validPlayers = players - lose

        if (dealer.isBusted()) {
            draw = emptyList()
            win = validPlayers
        } else if (dealer.isBlackJack()) {
            draw = validPlayers.filter { it.isBlackJack() }
            lose += validPlayers - draw
            win = emptyList()
        } else {
            draw = validPlayers.filter { it.score == dealer.score }
            lose += validPlayers.filter { it.score < dealer.score }
            win = validPlayers - draw - lose
        }
    }
}

