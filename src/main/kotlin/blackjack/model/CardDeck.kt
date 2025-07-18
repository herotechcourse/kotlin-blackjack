package blackjack.model

data class CardDeck(private val hold: MutableList<Card> = initPokerCards()) {
    val size
        get() = cards.size

    val cards
        get() = hold.toList()

    fun drawCard(): Card {
        val card = hold.first()
        hold.remove(card)
        return card
    }

    companion object {
        private fun initPokerCards() = Card.allCards().shuffled().toMutableList()
    }
}
