package blackjack.model

import blackjack.controller.Controller.Companion.INITIAL_CARD_COUNT

class Gambler(
    gamblerInfo: GamblerInfo,
    playerBet: PlayerBet,
) : Player(gamblerInfo, playerBet) {
    fun isPlayerBelowBlackJack(): Boolean = score < WINNING_SCORE

    fun hasCardCount(): Boolean = cards.size == INITIAL_CARD_COUNT

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
}
