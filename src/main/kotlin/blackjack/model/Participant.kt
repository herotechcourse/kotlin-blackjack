package blackjack.model

abstract class Participant(
    val name: String,
    val cardsInHand: Cards = Cards(mutableListOf())
) {
    var isBusted: Boolean = false
        private set

    fun drawCard(card: Card) {
        cardsInHand.addCard(card)
    }

    fun updateBustedStatus() {
            isBusted = true
    }

    fun checkCardsValueLimit() {
        if (cardsInHand.calculateTotalValueOfCards() > BlackJackValues.HAND_VALUE_LIMIT)
            updateBustedStatus()
    }

}
