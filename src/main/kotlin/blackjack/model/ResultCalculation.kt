package blackjack.model

object ResultCalculation {

    private fun hasPlayerBJ(player: Player) = player.cardsInHand.hasBlackJack()

    fun calculatePlayerResult(player: Player, dealer: Dealer): Int {
        val playerPoints = player.cardsInHand.calculateTotalValueOfCards()
        val dealerPoints = dealer.cardsInHand.calculateTotalValueOfCards()
        return when {
            player.cardsInHand.isBustHand() -> -player.bet
            dealer.cardsInHand.isBustHand() && hasPlayerBJ(player) -> player.bet * BlackJackValues.BET_MULTIPLICATOR.toInt()
            dealer.cardsInHand.isBustHand() -> player.bet
            playerPoints > dealerPoints && hasPlayerBJ(player) -> player.bet * BlackJackValues.BET_MULTIPLICATOR.toInt()
            playerPoints > dealerPoints -> player.bet
            playerPoints == dealerPoints -> 0
            else -> -player.bet
        }
    }
}
