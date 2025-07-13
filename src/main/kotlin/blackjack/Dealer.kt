package blackjack

class Dealer(val deck: Deck) : Participant("Dealer") {
    fun drawCard(): Card {
        return deck.drawCard()
    }

    override fun shouldHit(): Boolean {
        val scoreHand = this.sumCards()
        if (scoreHand < 17) {
            return true
        }
        return false
    }
}