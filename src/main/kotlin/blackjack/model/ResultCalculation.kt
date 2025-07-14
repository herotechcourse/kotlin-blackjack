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

    fun calculateDealerEarnings(dealer: Dealer, players: List<Player>): Int {
        val totalPlayersEarnings = players.sumOf { player -> player.earnings }
        return when {
            dealer.cardsInHand.hasBlackJack() && players.any { player -> player.cardsInHand.hasBlackJack() } -> 0
            dealer.cardsInHand.hasBlackJack() -> -totalPlayersEarnings
            !dealer.isPlaying -> -totalPlayersEarnings
            else -> 0
        }
    }
}
