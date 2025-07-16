package blackjack.model

abstract class Participant(
    val cardsInHand: Cards = Cards(mutableListOf())
) {

    var earnings: Int = 0

    fun drawCard(card: Card) {
        cardsInHand.addCard(card)
    }

    open fun updateEarnings(newEarnings: Int) {
        earnings += newEarnings
    }
}
