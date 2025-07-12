package model

class Dealer() : BasePlayer("Dealer") {
    private val deck = Deck()

    override fun makeDecision(): Boolean = getScore() <= 16

    fun dealCard(): Card = deck.pop()

    fun giveInitialCardsToPlayers(players: List<Player>) {
        repeat(2) {
            players.forEach { player -> player.drawCard(dealCard()) }
        }
    }

    fun drawInitialCards() {
        repeat(2) {
            drawCard(dealCard())
        }
    }
}
