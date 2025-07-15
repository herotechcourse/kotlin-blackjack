package blackjack.model

object ResultCalculation {

    private fun hasDealerBJ(dealer: Dealer) = dealer.cardsInHand.hasBlackJack()

    private fun hasPlayerBJ(player: Player) = player.cardsInHand.hasBlackJack()

    private fun hasAnyPlayerBJ(players: Players) = players.members.any { player -> player.cardsInHand.hasBlackJack() }

    fun calculatePlayerEarnings(player: Player, dealer: Dealer): Int {
        return when {
            hasDealerBJ(dealer) && hasPlayerBJ(player) -> 0
            hasPlayerBJ(player) -> (player.bet * BlackJackValues.BET_MULTIPLICATOR).toInt()
            player.isPlaying && !dealer.isPlaying -> player.bet
            !player.isPlaying -> -(player.bet)
            else -> 0
        }
    }

    fun calculateDealerEarnings(dealer: Dealer, players: Players): Int {
        val playersEarnings = players.calculateTotalPlayersEarning()
        var playersLoses = 0
        if (playersEarnings < 0)
            playersLoses = -(playersEarnings)
        return when {
            hasDealerBJ(dealer) && hasAnyPlayerBJ(players) -> 0
            hasDealerBJ(dealer) -> playersLoses
            dealer.isPlaying -> playersLoses
            !dealer.isPlaying -> -playersEarnings
            else -> 0
        }
    }
}
