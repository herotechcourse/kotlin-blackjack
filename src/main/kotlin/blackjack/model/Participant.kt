package blackjack.model

abstract class Participant(
    val cardsInHand: Cards = Cards(mutableListOf()),
) {

    open fun drawCard(card: Card) {
        cardsInHand.addCard(card)
    }
}
