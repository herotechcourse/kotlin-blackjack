package blackjack

class Dealer(val deck: Deck) : Participant("Dealer") {
    fun drawCard(): Card {
        return deck.drawCard()
    }
}