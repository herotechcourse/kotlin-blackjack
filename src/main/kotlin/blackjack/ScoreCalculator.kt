package blackjack

object ScoreCalculator {

    fun calculate(participant: Participant): Int {
        val hand = participant.getHand()
        var total = hand.sumOf { it.getScore() }
        var aces = hand.count { it.isAce() }

        while (total > 21 && aces > 0) {
            total -= 10
            aces--
        }

        return total
    }

    fun isBusted(participant: Participant): Boolean {
        return calculate(participant) > 21
    }
}
