package blackjack.model

class Player(override val name: String, override var isActive: Boolean = true) :
    Participant {
    override val cardsInHand: MutableList<Card> = mutableListOf()

    override fun drawCard(card: Card) {
        cardsInHand += card
    }

    override fun calculateTotalValueOfCards() = cardsInHand.sumOf { card -> card.rank.value }

    override fun updateActiveStatus(totalValueOfCards: Int) {
        if (totalValueOfCards > 21) {
            isActive = false
        }
    }
}

fun main() {
    val player = Player("Farhi")
    val card1 = Card(Rank.TWO, Suit.HEARTS)
    val card2 = Card(Rank.THREE, Suit.HEARTS)
    player.drawCard(card1)
    player.drawCard(card2)
    player.calculateTotalValueOfCards()
}
