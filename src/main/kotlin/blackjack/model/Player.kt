package blackjack.model

class Player(
    name: String,
) : Participant(name) {

    fun comparePointsAgainstDealer(dealerPoints: Int) {
        if (cardsInHand.calculateTotalValueOfCards() <= dealerPoints) {
            isBusted = true
        }
    }
}
