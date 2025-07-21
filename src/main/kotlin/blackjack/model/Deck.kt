package blackjack.model

class Deck(var cards: List<PlayingCard>) {
    fun shuffle(): Deck {
        cards = cards.shuffled()
        return this
    }

    private fun getCard(): PlayingCard {
        val deque = ArrayDeque(cards)
        val card = deque.first()
        deque.removeFirst()
        cards = deque.toList()
        return card
    }

    fun giveCard(): PlayingCard {
        val card = getCard()
        return card
    }
}
