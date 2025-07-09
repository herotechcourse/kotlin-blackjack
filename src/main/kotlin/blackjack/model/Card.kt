package blackjack.model

class Card(val rank: Rank, val suit: Suit, var visibility: Boolean = false) {
    override fun toString(): String {
        return "${rank.name} of ${suit.name}"
    }

    fun changeVisibilty() {
        visibility = true
    }
}
