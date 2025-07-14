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

    fun setWinnings(dealer: Dealer) {
        playerBet.winnings = playerBet.betAmount * calculateResult(dealer).multiplier
    }

    private fun calculateResult(dealer: Dealer): Result {
        return when {
            isBusted() -> Result.LOOSE
            isBlackJack() && dealer.isBlackJack() -> Result.TIE
            isBlackJack() -> Result.BLACKJACK
            dealer.isBusted() -> Result.WIN
            score == dealer.score -> Result.TIE
            score > dealer.score -> Result.WIN
            else -> Result.LOOSE
        }
    }

    companion object {
        private const val WIN_BLACKJACK_RETURN = 1.5
        private const val WIN_SIMPLE_RETURN = 1.0
    }
}
