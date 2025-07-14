package blackjack.state

import blackjack.model.card.Hand

class Bust(override val hand: Hand, override val rate: Double = 0.0) : Finished()
