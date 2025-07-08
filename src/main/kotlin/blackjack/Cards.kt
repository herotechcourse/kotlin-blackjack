package blackjack

class Cards(hold: HashSet<Card>) {
    constructor() : this(hashSetOf())

    private val _hold: MutableSet<Card> = hold.toMutableSet()
    val hold: HashSet<Card>
        get() = _hold.toCollection(LinkedHashSet())

    fun move(to: Cards) {
        val target = _hold.first()
        _hold.remove(target)
        to.add(target)
    }

    private fun add(card: Card) {
        _hold.add(card)
    }
}
