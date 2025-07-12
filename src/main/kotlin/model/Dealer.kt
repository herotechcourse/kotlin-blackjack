package model

class Dealer() : BasePlayer("Deal") {
    private val deck = Deck()

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

    fun makeDecision(): Boolean = getScore() <= 16

    fun dealCard(): Card = deck.pop()

    fun dealFromSelf() {
        drawCard(dealCard())
    }
}
