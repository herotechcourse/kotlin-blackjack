package blackjack.model.result

import blackjack.model.participant.Participants
import blackjack.model.participant.Player

class GameResult(val participants: Participants) {
    val dealer = participants.dealer
    val playerResults: List<PlayerResult> = getAllPlayerResults()

    private fun getAllPlayerResults(): List<PlayerResult> {
        return participants.players.map {
            PlayerResult(it, getPlayerResult(it))
        }
    }

    private fun getPlayerResult(player: Player): Outcome {
        return when {
            player.isBust() -> Outcome.LOSE
            player.isBlackjack() && !dealer.isBlackjack() -> Outcome.BLACKJACK
            dealer.isBust() -> Outcome.WIN
            else -> compareWithDealer(player)
        }
    }

    private fun compareWithDealer(player: Player): Outcome {
        return when {
            player.score > dealer.score -> Outcome.WIN
            player.score < dealer.score -> Outcome.LOSE
            else -> resolveEqualScore(player)
        }
    }

    private fun resolveEqualScore(player: Player): Outcome {
        val playerBlackjack = player.isBlackjack()
        val dealerBlackjack = dealer.isBlackjack()

        return when {
            playerBlackjack && dealerBlackjack -> Outcome.DRAW
            dealerBlackjack -> Outcome.LOSE
            playerBlackjack -> Outcome.WIN
            else -> Outcome.DRAW
        }
    }
}
