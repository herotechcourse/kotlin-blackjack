package blackjack.state

import blackjack.model.card.Hand

class Stand(override val hand: Hand, override val rate: Double = 1.0) : Finished()
