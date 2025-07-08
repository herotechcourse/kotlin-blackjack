package blackjack

class Cards(hold: Set<Card>) {
    constructor() : this(setOf())

    private val _hold: MutableSet<Card> = hold.toMutableSet()
    val hold: Set<Card>
        get() = _hold.toSet()

    fun move(to: Cards) {
        val target = _hold.first()
        _hold.remove(target)
        to.add(target)
    }

    private fun add(card: Card) {
        _hold.add(card)
    }

    operator fun plus(other: Cards): Cards {
        return Cards((other.hold + this.hold))
    }
}
