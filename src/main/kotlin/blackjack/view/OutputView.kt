package blackjack.view

import blackjack.model.cards.Card
import blackjack.model.table.Dealer
import blackjack.model.table.Participant
import blackjack.model.table.Players

object OutputView {
    fun displayFirstRoundMessage(players: Players) {
        println("\nDealing two cards to dealer, ${players.players.joinToString()}.")
    }

    fun displayAllCardsMessage(participant: Participant) {
        val cards = participant.state.hand.cards
        println("${participant.name}'s cards: ${cardsToString(cards)}")
    }

    fun displayFirstCardMessage(participant: Participant) {
        val firstCard = participant.state.hand.cards.first()
        println("${participant.name}: ${cardToString(firstCard)}")
    }

    private fun cardToString(card: Card): String {
        return card.rank.title + card.suit.symbol
    }

    private fun cardsToString(cards: List<Card>): String {
        val sb = StringBuilder()
        cards.forEach {
            sb.append(it.rank.title)
            sb.append(it.suit.symbol)
            sb.append(" ")
        }
        return sb.toString()
    }

    fun displayDealerDrawMessage() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayParticipantStatus(participant: Participant) {
        if (participant is Dealer) {
            println()
        }
        val cards = participant.state.hand.cards
        print("${participant.name}'s cards: ${cardsToString(cards)}")
        println(" – Total: ${participant.state.hand.total()}")
    }

    fun displayFinalResultString() {
        println("\n##Final Earnings")
    }

    fun displayResult(
        result: Double,
        participant: Participant,
    ) {
        println("${participant.name}: $result")
    }
}
