package blackjack.game

import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.state.Finished

object GameLogic {
    fun calculateProfitRates(
        dealer: Dealer,
        players: List<Player>,
    ) {
        val dealerFinalState =
            dealer.handState as? Finished
                ?: throw IllegalStateException("Dealer must be in a finished state to calculate profits.")

        var totalDealerProfitChange = 0.0

        players.forEach { player ->
            val playerFinalState = player.handState as Finished

            val playerProfit = playerFinalState.profit(dealerFinalState, player.bettingAmount)
            player.finalEarnings = playerProfit

            totalDealerProfitChange -= playerProfit
        }
        dealer.finalEarnings = totalDealerProfitChange
    }
}
