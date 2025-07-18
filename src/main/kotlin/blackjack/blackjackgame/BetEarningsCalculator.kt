package blackjack.blackjackgame

import blackjack.model.GameResult

object BetEarningsCalculator {
    fun calculateEarnings(
        result: GameResult,
        isBlackjack: Boolean,
        bet: Int,
    ): Int {
        return when {
            result == GameResult.WIN && isBlackjack -> (bet * 1.5).toInt()
            result == GameResult.WIN -> bet
            result == GameResult.LOSE -> -bet
            else -> 0
        }
    }
}
