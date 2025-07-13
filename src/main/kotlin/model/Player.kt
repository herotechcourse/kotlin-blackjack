package model

class Player(name: String) : BasePlayer(name) {
    fun requestCardFromDealer(dealer: Dealer) {
        val card = dealer.dealCard()
        drawCard(card)
    }
}
