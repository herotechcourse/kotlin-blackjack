package blackjack.states

import blackjack.model.Hand

class Stay(override val hand: Hand) : Finished
