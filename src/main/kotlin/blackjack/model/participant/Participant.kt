package blackjack.model.participant

import blackjack.model.card.Rank
import blackjack.model.holder.Hand

abstract class Participant(val name: String) : Hand() {
    val points = getPoints()

    private fun getPoints(): Int {
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
