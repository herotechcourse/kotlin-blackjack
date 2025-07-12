package blackjack.model

import blackjack.utils.Constants

data class Hand(val cards: List<Card> = emptyList()) {
    fun calculateCards(): Int {
        val values = cards.map { it.value }
        var aceCounts = values.count { it == Constants.ACE_HIGH }
        var score = values.sum()
        while (aceCounts > 0 && score > Constants.BLACK_JACK) {
            score -= Constants.ACE_HIGH - Constants.ACE_LOW
            aceCounts--
        }
        if (score < Constants.SCORE_THRESHOLD_FOR_ACE && values.contains(Constants.ACE_HIGH)) {
            score += Constants.ACE_HIGH - Constants.ACE_LOW
        }
        return score
    }

    fun toText(): String {
        return cards.joinToString(", ") { it.toString() }
    }
}
