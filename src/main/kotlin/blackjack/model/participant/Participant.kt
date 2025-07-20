package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.holder.Hand
import blackjack.model.state.State

abstract class Participant(val name: String, val amount: Int) : Hand() {
    val score get() = getCurrentPoints()

    protected abstract var mutableState: State
    abstract val state: State

    abstract fun isBust(): Boolean

    abstract fun isBlackjack(): Boolean

    private fun getCurrentPoints(): Int {
        return when (cards.isEmpty()) {
            true -> 0
            false -> calculatePoints()
        }
    }

    private fun calculatePoints(): Int {
        val aceCount = cards.filter { it.rank.value == Rank.ACE.value }.size
        var sum = cards.sumOf { it.rank.value }
        repeat(aceCount) { if (sum <= 11) sum += 10 }
        return sum
    }

    override fun receive(cards: List<Card>): Boolean {
        if (state != State.HIT) return false
        for (card in cards) {
            currentCards.add(card)
            if (state != State.HIT) return false
        }
        return true
    }
}
