package blackjack.model.result

import blackjack.model.participant.Dealer
import blackjack.model.participant.Participants
import blackjack.model.participant.Player
import blackjack.model.state.State

class GameResult(val participants: Participants) {
    val dealer = participants.dealer
    val playersResults: Map<Player, Outcome> = getAllResults()

    private fun getPlayerResult(player: Player): Outcome {
        return when {
            isPlayerBust(player) -> Outcome.LOSE
            isPlayerBlackjack(player, dealer) -> Outcome.BLACKJACK
            isDealerBust() -> Outcome.WIN
            else -> compareScores(player)
        }
    }

    private fun isPlayerBust(player: Player): Boolean {
        return player.state == State.BUST
    }

    private fun isPlayerBlackjack(
        player: Player,
        dealer: Dealer,
    ): Boolean {
        val playerHasBlackjack = player.state == State.BLACKJACK
        val dealerHasBlackjack = dealer.state == State.BLACKJACK

        return playerHasBlackjack && !dealerHasBlackjack
    }

    private fun isDealerBust(): Boolean {
        return dealer.state == State.BUST
    }

    private fun compareScores(player: Player): Outcome {
        return when {
            player.score > dealer.score -> Outcome.WIN
            player.score == dealer.score -> handleTie(player)
            else -> Outcome.LOSE
        }
    }

    private fun handleTie(player: Player): Outcome {
        val playerHasBlackjack = player.state == State.BLACKJACK
        val dealerHasBlackjack = dealer.state == State.BLACKJACK

        return when {
            playerHasBlackjack && dealerHasBlackjack -> Outcome.DRAW
            playerHasBlackjack && !dealerHasBlackjack -> Outcome.BLACKJACK
            !playerHasBlackjack && dealerHasBlackjack -> Outcome.LOSE
            else -> Outcome.DRAW
        }
    }
}
