package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players

object OutputView {

    private fun showCardFaceAndSymbol(card: Card): String {
        val face = RankView.fromRank(card.rank).face
        val symbol = SuitView.fromSuit(card.suit).symbol
        return "${face}${symbol}"
    }

    fun showDealerFirstHandCards(dealer: Dealer): String {
        return ("Dealer: ${showCardFaceAndSymbol(dealer.cardsInHand.cards[0])}")
    }

    fun showDealerHandCards(dealer: Dealer): String {
        return ("Dealer: ${
            dealer.cardsInHand.cards.joinToString(", ") { card ->
                showCardFaceAndSymbol(
                    card
                )
            }
        }")
    }

    fun showHandCards(player: Player): String {
        return ("${player.name}´s cards: ${
            player.cardsInHand.cards.joinToString(", ") { card ->
                showCardFaceAndSymbol(
                    card
                )
            }
        }")
    }

    fun displayInitialCards(
        dealer: Dealer,
        players: Players
    ) {
        val playerNames = players.getPlayers().joinToString(", ") { it.name }
        println("\nDealing two cards to Dealer, $playerNames.")
        println(showDealerFirstHandCards(dealer))
        players.getPlayers().forEach { player ->
            println(showHandCards(player))
        }
        println()
    }

    fun displayCardsAfterHit(player: Player) {
        println(showHandCards(player))
    }

    fun displayDealerDrawMessage() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayDealersCardsWithTotalValue(dealer: Dealer) {
        println(
            "${
                showDealerHandCards(
                    dealer
                )
            } - Total: ${dealer.cardsInHand.calculateTotalValueOfCards()}"
        )
    }

    fun displayCardsWithTotalValue(
        player: Player
    ) {
        println(
            "${
                showHandCards(
                    player
                )
            } - Total: ${player.cardsInHand.calculateTotalValueOfCards()}"
        )
    }

    fun displayFinalResults(
        dealer: Dealer,
        players: Players,
    ) {
        println("\n## Final Earnings")
        println("Dealer: ${dealer.earnings}")
        players.getPlayers().forEach {
            println("${it.name}: ${it.earnings}")
        }
    }
}
