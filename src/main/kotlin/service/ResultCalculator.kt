package service

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
