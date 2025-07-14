package service

import model.Dealer
import model.Players
import model.ResultStatus

class ResultCalculator {
    companion object {
        fun getResult(
            playersScores: List<Int>,
            dealerScore: Int,
        ): List<ResultStatus> {
            return playersScores.map { playerScore ->
                playerResult(playerScore, dealerScore)
            }
        }

        fun calculateEarning(
            allPlayers: Players,
            dealerScore: Int,
            dealerHasBlackJack: Boolean,
        ): List<PlayerEarningResult> {
            return allPlayers.players.mapIndexed { index, player ->
                val bet = player.bet.amount
                val playerScore = player.getScore()
                val playerHasBlackJack = player.isBlackJack()
                val (playerEarning, dealerEarning) =
                    when {
                        dealerScore > 21 -> bet to -bet
                        dealerHasBlackJack && !playerHasBlackJack -> -bet to bet
                        playerHasBlackJack && dealerHasBlackJack -> 0.0 to 0.0
                        playerScore == dealerScore -> 0.0 to 0.0
                        playerHasBlackJack -> bet * 1.5 to (-bet * 1.5)
                        playerScore > dealerScore && playerScore <= 21 -> bet to -bet
                        else -> -bet to bet
                    }
                PlayerEarningResult(index, playerEarning, dealerEarning)
            }
        }

        fun applyEarningResult(
            allPlayers: Players,
            dealer: Dealer,
            earnings: List<PlayerEarningResult>,
        ) {
            earnings.forEach { earning ->
                allPlayers.players[earning.playerId].earnings += earning.earningsChange
                dealer.earnings += earning.dealerEarningChange
            }
        }

        private fun playerResult(
            playerScore: Int,
            dealerScore: Int,
        ): ResultStatus {
            if (dealerScore > 21) return ResultStatus.WIN
            if (playerScore > dealerScore && playerScore <= 21) return ResultStatus.WIN
            if (playerScore == dealerScore) return ResultStatus.DRAW
            return ResultStatus.LOSS
        }
    }
}
