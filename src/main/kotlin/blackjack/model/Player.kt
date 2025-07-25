package blackjack.model

import blackjack.Constants.BLACKJACK_SCORE
import blackjack.view.ErrorPrompt.INPUT_INVALID_INTEGER
import blackjack.view.OutputPrompt.INVALID_NAME_EMPTY

class Player(
    val name: String,
    var betAmount: Int = 0,
    var earning: Double = 0.0,
    var status: Status = Status.Normal,
) {
    init {
        require(name.isNotBlank()) { INVALID_NAME_EMPTY }
        require(betAmount >= 0) { INPUT_INVALID_INTEGER }
    }

    private val _cards = Cards()
    var score: Int = 0
        private set

    val cards: List<Card>
        get() = _cards.cardList

    private fun updateScore() {
        var aceCount = _cards.cardList.count { it -> it.rank == Rank.ACE }
        var totalScore = _cards.cardList.sumOf { it -> it.rank.value }
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
        require(newAmount > 0) { "Bet amount must be positive" }
        betAmount = newAmount
    }

    fun updateEarning(newEarning: Double) {
        earning = newEarning
    }

    fun updateStatus() {
        status =
            when {
                score == BLACKJACK_SCORE && _cards.size == 2 -> Status.Blackjack
                score > BLACKJACK_SCORE -> Status.Busted
                else -> Status.Normal
            }
    }

    companion object {
        private const val WINNING_SCORE = 21
    }
}
