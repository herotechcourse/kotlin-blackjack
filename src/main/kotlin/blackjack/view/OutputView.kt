package blackjack.view

import blackjack.model.*

object OutputView {

    private fun showCardFaceAndSymbol(card: Card): String {
        val face = RankView.fromRank(card.rank).face
        val symbol = SuitView.fromSuit(card.suit).symbol
        return "${face}${symbol}"
    }

    fun showHandCards(participant: Participant, isFirstRound: Boolean): String {
        return when {
            participant is Dealer && isFirstRound -> "${participant.name}: ${showCardFaceAndSymbol(participant.cardsInHand.cards[0])}"

            else -> "${participant.name}´s cards: ${
                participant.cardsInHand.cards.joinToString(", ") { card ->
                    showCardFaceAndSymbol(
                        card
                    )
                }
            }"
        }
    }

    fun displayInitialCards(
        dealer: Dealer,
        players: List<Player>,
        isFirstRound: Boolean = true
    ) {
        val playerNames = players.joinToString(", ") { it.name }
        println("\nDealing two cards to ${dealer.name}, $playerNames.")
        println(showHandCards(dealer, isFirstRound))
        players.forEach { player ->
            println(showHandCards(player, isFirstRound))
        }
        println()
    }

    fun displayDealerDrawMessage(dealer: Dealer) {
        println("\n${dealer.name} draws one more card due to having 16 or less.")
    }

    fun displayCardsWithTotalValue(
        participant: Participant, isFirstRound: Boolean = false
    ) {
        println(
            "${
                showHandCards(
                    participant,
                    isFirstRound
                )
            } - Total: ${participant.cardsInHand.calculateTotalValueOfCards()}"
        )
    }

    fun displayFinalResults(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("\n## Final Earnings")
        println("${dealer.name}: ${dealer.earnings}")
        players.forEach {
            println("${it.name}: ${it.earnings}")
        }
    }
}
