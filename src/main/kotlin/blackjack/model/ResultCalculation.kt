package blackjack.model

object ResultCalculation {
    private fun hasPlayerBJ(player: Player) = player.cardsInHand.hasBlackJack()

    fun calculatePlayerResult(
        player: Player,
        dealer: Dealer,
    ): Int {
        val playerPoints = player.cardsInHand.calculateTotalValueOfCards()
        val dealerPoints = dealer.cardsInHand.calculateTotalValueOfCards()
        return when {
            player.cardsInHand.isBustHand() -> -player.wallet.bet
            dealer.cardsInHand.isBustHand() && hasPlayerBJ(player) -> player.wallet.bet * BlackJackValues.BET_MULTIPLICATOR.toInt()
            dealer.cardsInHand.isBustHand() -> player.wallet.bet
            playerPoints > dealerPoints && hasPlayerBJ(player) -> player.wallet.bet * BlackJackValues.BET_MULTIPLICATOR.toInt()
            playerPoints > dealerPoints -> player.wallet.bet
            playerPoints == dealerPoints -> 0
            else -> -player.wallet.bet
        }
    }
}
