package blackjack.state

import blackjack.model.card.Card
import blackjack.model.card.Hand

class Turn(override val hand: Hand = Hand(emptyList())) : Running() {
    constructor(cards: List<Card>) : this(Hand(cards))

    override fun draw(card: Card): State {
        this.hand.addCard(card)
        if (hand.size == 2) {
            if (hand.getScore() == 21) return Blackjack(hand)
            return Hit(hand)
        }
        return Turn(hand)
    }

    override fun stand(): State {
        throw IllegalStateException()
    }
}
