package blackjack

class Hold(hold: Set<Card>) {
    constructor() : this(setOf())

    private val _cards: MutableSet<Card> = hold.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun moveCard(to: Hold) {
        val target = _cards.first()
        _cards.remove(target)
        to.add(target)
    }

    private fun add(card: Card) {
        _hold.add(card)
    }

    operator fun plus(other: Hold): Hold {
        return Hold((other.cards + this.cards))
    }
}
