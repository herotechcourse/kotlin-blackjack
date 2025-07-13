package blackjack.model

data class CardDeck(private val hold: List<Card>) {
    constructor() : this(initPokerCards())

    val size
        get() = cards.size

    val cards
        get() = hold.toList()

    companion object {
        private fun initPokerCards() = Card.allCards().shuffled()
    }
}
