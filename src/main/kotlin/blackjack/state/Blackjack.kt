package blackjack.state

import blackjack.model.card.Hand

class Blackjack(override val hand: Hand, override val rate: Double = 1.5) : Finished()
