package model

class Dealer() : BasePlayer("Dealer") {
    private val deck = Deck()

    fun dealCard(): Card = deck.pop()

    override fun toString(): String {
        return "$name's cards: $hand"
    }

    override fun makeDecision(value: Int): Boolean {
        return value >= 16
    }
}
