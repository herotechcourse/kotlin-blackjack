package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.Rank
import blackjack.model.holder.Hand
import blackjack.model.state.State

abstract class Participant(val name: String) : Hand() {
    val score get() = getCurrentPoints()

    abstract var currentState: State
    val state get() = currentState

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

    override fun receive(card: Card): Boolean {
        if (currentState != State.HIT) return false

        currentCards.add(card)
        return true
    }
}
