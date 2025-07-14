package blackjack.model

class Player(
    name: String,
) : Participant(name) {
    var bet: Int = 0
        private set

    fun hasLessPointsThanDealer(dealerPoints: Int) = cardsInHand.calculateTotalValueOfCards() <= dealerPoints

    fun setBet(bet: Int) {
        this.bet = bet
    }
}
