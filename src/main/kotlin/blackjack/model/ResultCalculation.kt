package blackjack.model

object ResultCalculation {

    fun calculatePlayerEarnings(player: Player, dealer: Dealer): Int {
        return when {
            dealer.cardsInHand.hasBlackJack() && player.cardsInHand.hasBlackJack() -> 0
            player.cardsInHand.hasBlackJack() -> (player.bet * BlackJackValues.BET_MULTIPLICATOR).toInt()
            player.isPlaying && !dealer.isPlaying -> player.bet
            !player.isPlaying -> -(player.bet)
            else -> 0
        }
    }

    fun calculateDealerEarnings(dealer: Dealer, players: Players): Int {
        val playersEarnings = players.calculateTotalPlayersEarning()
        return when {
            dealer.cardsInHand.hasBlackJack() && players.getPlayers().any { player -> player.cardsInHand.hasBlackJack() } -> 0
            dealer.cardsInHand.hasBlackJack() -> - playersEarnings
            dealer.isPlaying && playersEarnings < 0 -> - playersEarnings
            !dealer.isPlaying -> - playersEarnings
            else -> 0
        }
    }
}
