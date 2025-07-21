package blackjack.model.result

class DealerEarning(playerResults: List<PlayerResult>) {
    val amount: Int = calculateEarningAmount(playerResults)

    private fun calculateEarningAmount(playerResults: List<PlayerResult>): Int {
        val totalPlayerWinnings =
            playerResults.filter {
                it.outcome != Outcome.LOSE
            }.sumOf { playerResult ->
                playerResult.finalAmount
            }

        val totalPlayerBets = playerResults.sumOf { playerResult -> playerResult.player.amount }
        return totalPlayerBets - totalPlayerWinnings
    }
}
