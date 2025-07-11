package blackjack.model

class Gambler(gamblerInfo: GamblerInfo) : Player(gamblerInfo) {
    fun isPlayerBelowBlackJack(): Boolean = score < WINNING_SCORE
}
