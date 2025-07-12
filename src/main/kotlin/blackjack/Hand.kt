package blackjack

class Hand () {
    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun sumCards(): Int {
        val cardValues = cards.map{ card -> card.number }
        return cardValues.sum()
    }
}