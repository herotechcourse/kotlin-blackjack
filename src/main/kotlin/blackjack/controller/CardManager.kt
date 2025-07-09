package blackjack.controller

import blackjack.model.Card
import blackjack.utils.CardGenerator

class CardManager(generator: CardGenerator = CardGenerator) {
    private var _cards = generator.generateCards().shuffled()
    val cards: List<Card>
        get() = _cards

    private var _cardMap = initiateCardMap()
    val cardMap: Map<Card, Boolean>
        get() = _cardMap

    private fun initiateCardMap(): Map<Card, Boolean> {
        val map = mutableMapOf<Card, Boolean>()
        cards.forEach { card ->
            map[card] = true
        }
        return map
    }

    fun checkCardMap(card: Card): Boolean {
        return cardMap.getOrDefault(card, false)
    }

    private fun updateCardMap(card: Card) {
        val map = cardMap.toMutableMap()
        map[card] = false
        _cardMap = map.toMap()
    }

    private fun getCard(): Card {
        val deque = ArrayDeque(cards)
        val card = deque.first()
        deque.removeFirst()
        _cards = deque.toList()
        return card
    }

    fun giveCard(): Card {
        val card = getCard()
        updateCardMap(card)
        return card
    }
}
