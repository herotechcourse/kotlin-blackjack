package blackjack.model.result

import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import blackjack.model.state.State

class GameResult(private val participants: Participants) {
    val dealer = participants.dealer
    val playersResults: Map<Player, Outcome> = getAllResults()

    private fun getPlayerResult(player: Player): Outcome {
        return when {
            player.state == State.BUST -> Outcome.LOSE
            dealer.state == State.BUST -> Outcome.WIN
            player.score > dealer.score -> Outcome.WIN
            player.score == dealer.score -> Outcome.DRAW
            else -> Outcome.LOSE
        }
    }

    private fun getAllResults(): Map<Player, Outcome> {
        return participants.players.associateWith { getPlayerResult(it) }
    }
}
