package blackjack.model

abstract class Participant(
    val name: String,
    var isBusted: Boolean = false,
    val cardsInHand: Cards = Cards(mutableListOf())
) {

    fun drawCard(card: Card) {
        cardsInHand.addCard(card)
    }

    fun updateBustedStatus() {
        if (cardsInHand.calculateTotalValueOfCards() > BlackJackValues.HAND_VALUE_LIMIT) {
            isBusted = true
        }
    }
}
