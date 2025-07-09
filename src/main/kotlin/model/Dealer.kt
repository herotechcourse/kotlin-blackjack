package model

class Dealer() : BasePlayer("Dealer") {
    private val deck = Deck()

    override fun makeDecision(): Boolean {
        return getScore() <= 16
    }

    fun dealCard(): Card = deck.pop()

    override fun toString(): String {
        return "$name's cards: $hand"
    }
}
