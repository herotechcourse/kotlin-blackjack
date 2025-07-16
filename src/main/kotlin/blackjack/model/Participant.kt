package blackjack.model

abstract class Participant(
    val cardsInHand: Cards = Cards(mutableListOf()),
) {
    fun drawCard(card: Card) {
        cardsInHand.addCard(card)
    }

    fun isAbleToHit(): Boolean {
        return cardsInHand.isLowHand()
    }
}
