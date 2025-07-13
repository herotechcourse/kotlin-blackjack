package blackjack.model

abstract class Participant {
    abstract val name: String
    abstract val handCards: HandCards

    fun drawCard(deck: CardDeck) {
        val card = deck.hit()
        handCards.add(card)
    }

    fun isNotBusted(): Boolean = (handCards.total <= Rules.BLACKJACK_TARGET)

    fun isBusted(): Boolean = (handCards.total > Rules.BLACKJACK_TARGET)

    fun cardsToString(): String {
        return handCards.cards.joinToString()
    }

    fun firstCardToString(): String {
        return handCards.cards[0].toString()
    }
}
