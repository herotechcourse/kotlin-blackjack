package blackjack.model

class Player(name: String, val bet: Int) : Participant(name) {
    private var winningMoney: Int = 0

    fun drawAndUpdate(deck: Deck) {
        drawCard(deck.draw())
    }

    fun updateWinningMoney(dealer: Dealer) {
        winningMoney =
            when {
                isBlackjack() && dealer.isBlackjack() -> bet
                isBlackjack() -> (bet * 1.5).toInt()
                dealer.isBusted() -> bet
                total() > dealer.total() -> bet
                total() == dealer.total() -> 0
                else -> -bet
            }
    }

    fun returnWinningMoneyForPlayer(): Int {
        return winningMoney
    }
}
