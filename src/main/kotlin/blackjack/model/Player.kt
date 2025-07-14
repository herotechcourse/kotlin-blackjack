package blackjack.model

class Player(name: String) : Participant(name) {
    fun drawAndUpdate(deck: Deck) {
        drawCard(deck.draw())
    }
}
