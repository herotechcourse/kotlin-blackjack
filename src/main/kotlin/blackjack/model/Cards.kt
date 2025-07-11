package blackjack.model

class Cards(hold: Set<Card>) {
    private val _cards: MutableSet<Card> = hold.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun moveCard(to: Player) {
        val target = _cards.first()
        _cards.remove(target)
        to.addCard(target)
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    operator fun plus(other: Cards): Cards {
        return Cards((other.cards + this.cards))
    }
}
