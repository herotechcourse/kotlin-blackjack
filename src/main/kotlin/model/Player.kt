package model

class Player(name: String) : BasePlayer(name) {
    override fun toString(): String {
        return "$name's cards: $hand"
    }

    override fun turn(
        deck: Deck,
        doAfter: (String) -> Unit,
        decision: () -> Boolean,
    ) {
        while (decision()) {
            drawCard(deck.pop())
            doAfter(name)
        }
    }
}
