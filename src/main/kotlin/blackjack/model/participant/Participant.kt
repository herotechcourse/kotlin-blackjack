package blackjack.model.participant

import blackjack.model.card.Rank
import blackjack.model.holder.Hand
import blackjack.model.state.State

abstract class Participant(val name: String) : Hand() {
    val points get() = getCurrentPoints()

    abstract var _state: State
    val state get() = _state

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
}
