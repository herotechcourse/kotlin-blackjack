package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

class Busted(hand: Hand, deck: Deck) : Finished(hand, deck)
