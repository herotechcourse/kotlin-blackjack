package blackjack.model

import blackjack.model.participant.Dealer
import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import blackjack.model.participant.vs

object GameJudge {
    fun evaluateAll(participants: Participants) {
        participants.players.forEach { evaluate(participants.dealer, it) }
    }

    internal fun evaluate(
        dealer: Dealer,
        player: Player,
    ) {
        val result = dealer vs player
        player.recordResult(result)
        dealer.recordResult(result.inverse)
    }
}
