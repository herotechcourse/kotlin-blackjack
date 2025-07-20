package model

class Player(name: String) : BasePlayer(name) {
    var bet = 0

    override fun toString(): String {
        return "$name's cards: $hand"
    }

    override fun turn(
        deck: Deck,
        doAfter: (BasePlayer) -> Unit,
        decision: (BasePlayer) -> Boolean,
    ) {
        while (decision(this)) {
            drawCard(deck.pop())
            doAfter(this)
        }
    }
}
