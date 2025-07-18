package blackjack.model

data class CardDeck(private val hold: MutableList<Card> = initPokerCards()) {
    val size: Int
        get() = cards.size

    val cards: List<Card>
        get() = hold.toList()

    fun drawCard(): Card {
        hold.ifEmpty { hold.addAll(initPokerCards()) }
        return hold.removeFirst()
    }

    companion object {
        private fun initPokerCards() = Card.allCards().shuffled().toMutableList()
    }
}
