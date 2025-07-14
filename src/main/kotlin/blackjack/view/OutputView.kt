package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Participant
import blackjack.model.Player

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

    fun getFinalResultForDealer(dealer: Dealer, players: List<Player>): String {
        val lose = players.count { it.isPlaying }
        if (!dealer.isPlaying)
            return "0 Win $lose Lose"
        val win = players.count { !it.isPlaying }
        return "$win Win $lose Lose"
    }

    fun displayFinalResults(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("\n## Final Results")
        println("${dealer.name}: ${getFinalResultForDealer(dealer, players)}")
        players.forEach {
            print("${it.name}: ")
            if (it.isPlaying) println("Win") else println("Lose")
        }
    }
}
