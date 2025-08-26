package blackjack.state

import blackjack.model.Deck
import blackjack.model.Hand

class Stay(hand: Hand, deck: Deck) : Finished(hand, deck)
