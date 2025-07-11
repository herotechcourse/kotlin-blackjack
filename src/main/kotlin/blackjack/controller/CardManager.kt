package blackjack.controller

import blackjack.model.PlayingCard

class CardManager() {
    var cards: List<PlayingCard> = emptyList()
    private var _cardMap: Map<PlayingCard, Boolean> = emptyMap()
    val cardMap: Map<PlayingCard, Boolean>
        get() = _cardMap

    init {
        cards = PlayingCard.Deck.toList().shuffled()
        _cardMap = initiateCardMap()
    }

    private fun initiateCardMap(): Map<PlayingCard, Boolean> {
        val map = mutableMapOf<PlayingCard, Boolean>()
        cards.forEach { card ->
            map[card] = true
        }
        return map
    }

    fun checkCardMap(card: PlayingCard): Boolean {
        return cardMap.getOrDefault(card, false)
    }

    private fun updateCardMap(card: PlayingCard) {
        val map = cardMap.toMutableMap()
        map[card] = false
        _cardMap = map.toMap()
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
        updateCardMap(card)
        return card
    }
}
