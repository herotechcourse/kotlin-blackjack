package blackjack.state

import blackjack.model.card.Card
import blackjack.model.card.Hand

class Hit(override val hand: Hand) : Running() {
    init {
        require(hand.size >= 2)
    }

    override fun draw(card: Card): State {
        this.hand.addCard(card)
        if (hand.getScore() > 21) return Bust(hand)
        return Hit(hand)
    }

    override fun stand(): State {
        return Stand(hand)
    }
}
