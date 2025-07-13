package model

class Dealer() : BasePlayer() {
    private val deck = Deck()

    fun giveInitialCardsToPlayers(allPlayers: Players) {
        repeat(2) {
            allPlayers.players.forEach { player -> player.drawCard(dealCard()) }
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
