package blackjack.model

data class Card(val suit: Suit, val rank: Rank)

fun Card.toText(): String {
    return "${this.rank.face}${this.suit.symbol}"
}
