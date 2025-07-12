package blackjack.model

class Card(val rank: Rank, val suit: Suit) {
    override fun toString(): String {
        return "${rank.name} of ${suit.name}"
    }

    fun showCardFace():String{
        return "${rank.face}${suit.symbol}"
    }
}
