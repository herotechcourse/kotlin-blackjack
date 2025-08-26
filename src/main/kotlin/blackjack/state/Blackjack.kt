package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

class Blackjack(hand: Hand, deck: Deck) : Finished(hand, deck)
