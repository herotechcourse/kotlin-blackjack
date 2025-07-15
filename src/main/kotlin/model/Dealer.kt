package model

class Dealer() : BasePlayer("Dealer") {
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
