package blackjack.model

class Player(
    name: String,
) : Participant(name) {

    fun hasLessPointsThanDealer(dealerPoints: Int) = cardsInHand.calculateTotalValueOfCards() <= dealerPoints

}
