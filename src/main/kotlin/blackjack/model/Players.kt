package blackjack.model

import blackjack.view.InputView

class Players(private val players: List<Player>) : Iterable<Player> {

    override fun iterator(): Iterator<Player> = players.iterator()

    fun dealFirstCards(dealer: Dealer) {
        repeat(2) {
            dealer.addCard(dealer.dealCard())
            players.forEach { it.addCard(dealer.dealCard()) }
        }
    }

    fun dealingPlayersCards(retry: (() -> String) -> String, dealer: Dealer) {
        for (player in players) {
            while (!player.hasBlackJack() && !player.isBusts()) {
                val answer = retry { InputView.getAnswer(player.name) }
                if (answer == "y") {
                    player.addCard(dealer.dealCard())
                    println(player)
                } else {
                    break
                }
            }
        }
    }

    fun calculateResults(dealer: Dealer) {
        players.forEach { dealer.setResultFor(it) }
    }

    fun toList(): List<Player> = players

    fun size() = players.size

}
