package blackjack.model

class Hand(val cards: List<PlayingCard> = emptyList()) {
    fun calculateHand(): Int {
        val values = cards.map { it.value }
        var aceCounts = values.count { it == 11 }
        var score = values.sum()
        while (aceCounts > 0 && score > 21) {
            score -= 10
            aceCounts--
        }
        if (score < 10 && values.contains(11)) {
            score += 10
        }
        return score
    }

    fun toText(): String {
        val forms = cards.map { it.string }
        return forms.joinToString(", ")
    }
}
