package blackjack.model.result

class DealerEarning(playerResults: List<PlayerResult>) {
    val amount: Int = calculateEarningAmount(playerResults)

    internal fun calculateEarningAmount(playerResults: List<PlayerResult>): Int {
        val totalPlayerWinnings =
            playerResults.filter { (player, outcome) ->
                outcome != Outcome.LOSE
            }.sumOf { playerResult ->
                playerResult.finalAmount
            }

        val totalPlayerBets = playerResults.sumOf { playerResult -> playerResult.player.amount }
        return totalPlayerBets - totalPlayerWinnings
    }
}
