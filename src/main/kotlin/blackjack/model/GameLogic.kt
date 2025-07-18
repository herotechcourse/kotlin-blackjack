package blackjack.model

object GameLogic {
    fun calculateProfitRates(
        dealer: Dealer,
        players: List<Player>,
    ): Map<Player, Double> {
        return players.associateWith { it.state.profit(dealer.state, it.bettingAmount) }
    }
}
