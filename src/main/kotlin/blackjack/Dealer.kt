package blackjack

class Dealer {
    fun firstTurnCards(deck: List<Card>): List<Card> {
        val hand = mutableListOf<Card>()
        repeat(2, { hand.add(takeRandomCard(deck)) })
        hand.forEach { card ->
            println("random cards: ${card.suit}, ${card.number}")
        }
        return hand
    }

    private fun takeRandomCard(deck: List<Card>): Card {
        return deck.random()
    }
}