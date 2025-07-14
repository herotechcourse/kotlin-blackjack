package blackjack.model.participant

class Participants(val dealer: Dealer, val players: List<Player>) {
    val dealerScore: Int
        get() = dealer.getScore()
    val dealerName: String
        get() = dealer.name
    val dealerResult: String
        get() = dealer.result

    fun dealOneCardToAll() {
        players.forEach { it.addCard(dealer.dealCard()) }
        dealer.addCard(dealer.dealCard())
    }

    fun sortAllHands() {
        players.forEach { it.sortHand() }
    }
}
