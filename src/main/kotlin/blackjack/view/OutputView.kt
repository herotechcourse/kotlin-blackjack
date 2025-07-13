package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.DealerResult
import blackjack.model.Participant
import blackjack.model.PlayerResult
import blackjack.model.Players
import blackjack.model.Suit

object OutputView {
    private val suitSymbol =
        mapOf(
            Suit.HEARTS to "♥",
            Suit.DIAMONDS to "♦",
            Suit.CLUBS to "♣",
            Suit.SPADES to "♠",
        )

    fun displayPlayerNames(players: Players) {
        println(players.players.joinToString())
    }

    fun displayInitialCardsMessage(players: Players) {
        println("\nDealing two cards to dealer, ${players.players.joinToString()}.")
    }

    fun displayAllCardsMessage(participant: Participant) {
        println("${participant.name}'s cards: ${cardsToString(participant.handCards.cards)}")
    }

    fun displayFirstCardMessage(participant: Participant) {
        val firstCard = participant.handCards.cards.first()
        println("${participant.name}: ${formatCard(firstCard)}")
    }

    fun displayDealerDrawMessage() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayParticipantStatus(participant: Participant) {
        if (participant is Dealer) {
            println()
        }
        println("${participant.name}'s cards: ${cardsToString(participant.handCards.cards)} – Total: ${participant.handCards.total}")
    }

    private fun formatDealer(result: DealerResult): String = "Dealer: ${result.wins} wins, ${result.losses} losses, ${result.draws} draws"

    private fun formatPlayer(result: PlayerResult): String =
        when {
            result.win -> "${result.name}: win"
            result.draw -> "${result.name}: draw"
            else -> "${result.name}: loss"
        }

    // TODO: refactor Pair to value class?
    fun displayResults(results: Pair<DealerResult, List<PlayerResult>>) {
        println(
            """
            |
            |## Final Results:
            |${formatDealer(results.component1())}
            |${results.component2().joinToString("\n") { formatPlayer(it) }}
            """.trimMargin(),
        )
    }

    private fun cardsToString(cards: List<Card>): String = cards.joinToString(" ") { formatCard(it) }

    private fun formatCard(card: Card): String = "${card.rank.title}${suitSymbol.getValue(card.suit)}"
}
