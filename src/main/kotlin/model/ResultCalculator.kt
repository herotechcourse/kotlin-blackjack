package model

class ResultCalculator {
    companion object {
        fun ratio(
            playerScore: Int,
            dealerScore: Int,
        ): Double {
            return when {
                isBusted(playerScore) -> -1.0
                isBusted(dealerScore) && playerScore < 21 -> 1.0
                isBlackJack(playerScore) && dealerScore < 21 -> 1.5
                playerScore > dealerScore -> 1.0
                playerScore < dealerScore -> -1.0
                else -> 0.0
            }
        }

        private fun isBlackJack(score: Int): Boolean = score == 21

        private fun isBusted(score: Int): Boolean = score > 21
    }
}
