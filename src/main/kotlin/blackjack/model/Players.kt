package blackjack.model

class Players(private val players: List<Player>) : Iterable<Player> {
    override fun iterator(): Iterator<Player> = players.iterator()

    fun dealFirstCards(dealer: Dealer) {
        repeat(2) {
            dealer.addCard(dealer.dealCard())
            players.forEach { it.addCard(dealer.dealCard()) }
        }
    }

    fun dealingPlayersCards(): List<Player> {
        return players.filter { !it.isFinished() }
    }

    fun calculateResults(dealer: Dealer) {
        players.forEach { dealer.setResultFor(it) }
    }

    fun toList(): List<Player> = players

    fun size() = players.size
}
