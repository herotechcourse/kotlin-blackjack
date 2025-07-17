package blackjack.model

abstract class Participant {
    abstract val name: String
    abstract val handCards: HandCards
    abstract val bet: Int
    var earning: Int = 0

    fun drawCard(deck: CardDeck) {
        val card = deck.hit()
        handCards.add(card)
    }

    fun isNotBusted(): Boolean = (handCards.total <= Rules.BLACKJACK_TARGET)

    fun isBusted(): Boolean = (handCards.total > Rules.BLACKJACK_TARGET)

    fun settlePlayerAndDealer(dealer: Dealer) {
        earning = when {
            isBusted()                                                  -> -bet
            dealer.isBusted()                                           -> +bet
            
            handCards.isBlackjack() && dealer.handCards.isBlackjack()   -> 0
            handCards.isBlackjack()                                     -> (bet * 1.5).toInt()
            dealer.handCards.isBlackjack()                              -> -bet
            
            handCards.total > dealer.handCards.total                    -> +bet
            handCards.total < dealer.handCards.total                    -> -bet
            
            else                                                        -> 0
        }

        dealer.addEarning(-earning) // subtract Player's earning from Dealer's earnings
    }
}
