package blackjack.model

import blackjack.controller.Controller.Companion.BLACKJACK_SCORE
import blackjack.view.ErrorPrompt.INPUT_INVALID_INTEGER
import blackjack.view.OutputPrompt.INVALID_NAME_EMPTY

class Player(val name: String, var betAmount: Int = 0) {
    init {
        require(name.isNotBlank()) { INVALID_NAME_EMPTY }
        require(betAmount >= 0) { INPUT_INVALID_INTEGER }
    }
    
    private val _cards = Cards()
    var score: Int = 0
        private set

    val cards: List<Card>
        get() = _cards.cards

    private fun updateScore() {
        var aceCount = _cards.cards.count { it -> it.rank == Rank.ACE }
        var totalScore = _cards.cards.sumOf { it -> it.rank.value }
        while (totalScore > WINNING_SCORE && aceCount > 0) {
            totalScore -= 10
            aceCount--
        }
        score = totalScore
    }

    fun addCard(cards: List<Card>) {
        _cards.addAll(cards)
        updateScore()
    }

    fun updateBetAmount(newAmount: Int) {
        require(newAmount > 0) { INPUT_INVALID_INTEGER }
        betAmount = newAmount
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
