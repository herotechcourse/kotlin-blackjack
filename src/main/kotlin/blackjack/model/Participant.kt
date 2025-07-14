package blackjack.model

abstract class Participant {
    abstract val name: String
    abstract val handCards: HandCards
    abstract val bet: Int
    var earning: Int = 0

    fun drawCard(deck: CardDeck) {
        val card = deck.hit()
        handCards.add(card)
    }

    fun isNotBusted(): Boolean = (handCards.total <= Rules.BLACKJACK_TARGET)

    fun isBusted(): Boolean = (handCards.total > Rules.BLACKJACK_TARGET)
}
