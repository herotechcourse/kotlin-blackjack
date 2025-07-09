package blackjack.model

class Hand() {
    private val hold: Hold = Hold(setOf())

    fun numberOfCards() = hold.cards.size

    fun addCard(card: Card) = this.hold.add(card)
}
