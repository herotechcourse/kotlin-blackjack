package blackjack.model


class FinalResult(val dealer: Dealer, players: List<Gambler>) {
    var lose: List<Gambler> = emptyList()
    var draw: List<Gambler> = emptyList()
    var win: List<Gambler> = emptyList()

    init {
        when {
            dealer.isBusted() -> {
                win = players.filterNot { it.isBusted() }
                lose = players.filter { it.isBusted() }
            }

            dealer.isBlackJack() -> {
                draw = players.filter { it.isBlackJack() }
                lose = players - draw
            }

            else -> {
                draw = players.filter { it.score == dealer.score }
                lose = players.filter { it.score < dealer.score }
                win = players - draw - lose
            }
        }
    }
}
