package model

class Player(val name: String) : BasePlayer() {
    init {
        require(name.isNotEmpty() && name.isNotBlank()) { "Name must not be empty" }
    }
    fun requestCardFromDealer(dealer: Dealer) {
        val card = dealer.dealCard()
        drawCard(card)
    }
}
