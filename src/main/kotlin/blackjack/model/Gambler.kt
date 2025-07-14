package blackjack.model

import blackjack.controller.Controller.Companion.INITIAL_CARD_COUNT

class Gambler(gamblerInfo: GamblerInfo) : Player(gamblerInfo) {
    fun isPlayerBelowBlackJack(): Boolean = score < WINNING_SCORE

    fun hasCardCount(): Boolean = cards.size == INITIAL_CARD_COUNT

    fun calculateAndSetWinnings(isWin: Boolean) {
        if (!isWin) {
            playerBet.winnings = -playerBet.betAmount
            return
        }

        if (isBlackJack()) {
            playerBet.winnings = playerBet.betAmount * WIN_BLACKJACK_RETURN
        } else {
            playerBet.winnings = playerBet.betAmount * WIN_SIMPLE_RETURN
        }
    }

    companion object {
        private const val WIN_BLACKJACK_RETURN = 1.5
        private const val WIN_SIMPLE_RETURN = 1.0
    }
}
