package blackjack.model.holder

import blackjack.model.card.Card

open class Hand : CardHolder() {
    override fun onDrawFailed(): Card {
        throw IllegalStateException(HAND_EMPTY_BUT_TRY_TO_DRAW)
    }

    companion object {
        private const val HAND_EMPTY_BUT_TRY_TO_DRAW = "Logic error: Hand try to draw, Even not hold any Card."
    }
}
