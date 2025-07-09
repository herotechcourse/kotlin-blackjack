package blackjack.model

class Hand(private val hold: Hold) {
    constructor() : this(Hold(setOf()))

    fun numberOfCards(): Int {
        return hold.cards.size
    }

    fun addCard(card: Card) {
        this.hold.add(card)
    }
}
