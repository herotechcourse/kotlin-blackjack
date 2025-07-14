package blackjack.model

import blackjack.controller.Controller.Companion.INITIAL_CARD_COUNT

class Gambler(gamblerInfo: GamblerInfo) : Player(gamblerInfo) {
    fun isPlayerBelowBlackJack(): Boolean = score < WINNING_SCORE

    fun hasCardCount(): Boolean = cards.size == INITIAL_CARD_COUNT
}
