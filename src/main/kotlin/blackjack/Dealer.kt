package blackjack

class Dealer(val deck: Deck) : Participant("Dealer") {
    fun drawCard(): Card {
        return deck.drawCard()
    }

    override fun shouldHit(): Boolean {
        val scoreHand = this.sumCards()
        if (scoreHand < 17) {
            println()
            println("Dealer draws one more card due to having 16 or less.")
            println()
            return true
        }
        return false
    }
}