package blackjack.model

data class Hand(val cards: List<Card> = emptyList()) {
    fun calculateCards(): Int {
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
        val names = cards.map { it.name }
        return names.joinToString(", ")
    }
}
