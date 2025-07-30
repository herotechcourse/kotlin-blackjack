package model

class Players(val value: List<Player>) {
    fun setBet(doRequest: (BasePlayer) -> Int) {
        value.forEach {
            apply {
                it.bet = doRequest(it)
            }
        }
    }

    fun turn(
        deck: Deck,
        doAfter: (BasePlayer) -> Unit,
        decision: (BasePlayer) -> Boolean = { true },
    ) {
        value.forEach { it.turn(deck, doAfter, decision) }
    }

    fun init(deck: Deck) {
        value.forEach { it.init(deck) }
    }
}
