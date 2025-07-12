package blackjack.model

class Dealer(name: String = "Dealer") : Participant( name) {
    fun mustDraw(totalValueOfCards: Int) = totalValueOfCards <= 16


}
