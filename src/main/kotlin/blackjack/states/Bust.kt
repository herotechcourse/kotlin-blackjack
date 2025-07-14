package blackjack.states

import blackjack.model.Hand

class Bust(override val hand: Hand) : Finished
