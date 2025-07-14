package blackjack.model

import blackjack.controller.Controller.Companion.BLACKJACK_SCORE

class Player(val gamblerInfo: GamblerInfo) {
    private val _cards = Cards()
    var score: Int = 0
        private set

    val cards: List<Card>
        get() = _cards.cards

    val name: String
        get() = gamblerInfo.name

    fun addCard(cards: List<Card>) {
        _cards.addAll(cards)
        updateScore()
    }

    private fun updateScore() {
        var aceCount = _cards.cards.count { it -> it.rank == Rank.ACE }
        var totalScore = _cards.cards.sumOf { it -> it.rank.value }
        while (totalScore > WINNING_SCORE && aceCount > 0) {
            totalScore -= 10
            aceCount--
        }
        score = totalScore
    }

    fun isBlackJack(): Boolean {
        return score == BLACKJACK_SCORE && _cards.size == 2
    }

    fun isBusted(): Boolean {
        return score > BLACKJACK_SCORE
    }

    companion object {
        private const val WINNING_SCORE = 21
    }
}
