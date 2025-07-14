package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Suit

object OutputView {
    private fun formatCard(card: Card): String {
        val rankFace =
            when (card.rank.name) {
                "TEN" -> "10"
                "JACK" -> "J"
                "QUEEN" -> "Q"
                "KING" -> "K"
                "ACE" -> "A"
                else -> card.rank.value.toString()
            }

        val suitSymbol =
            when (card.suit) {
                Suit.HEARTS -> "♥"
                Suit.DIAMONDS -> "♦"
                Suit.CLUBS -> "♣"
                Suit.SPADES -> "♠"
            }
        return "$rankFace$suitSymbol"
    }

    fun showInitialCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println()
        val playerNames = players.joinToString(", ") { it.name }
        println("Dealing two cards to dealer, $playerNames.")

        println("Dealer: ${formatCard(dealer.cardsInHand[0])}")

        players.forEach {
            println("${it.name}'s cards: ${it.cardsInHand.joinToString(", ") { card -> formatCard(card) }}")
        }
        println()
    }

    fun showPlayerCards(player: Player) {
        println("${player.name}'s cards: ${player.cardsInHand.joinToString(", ") { formatCard(it) }}")
    }

    fun showDealerDrawsCard() {
        println("Dealer draws one more card due to having 16 or less.\n")
    }

    fun showFinalHands(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("Dealer's cards: ${dealer.cardsInHand.joinToString(", ") { formatCard(it) }} – Total: ${dealer.total()}")
        players.forEach {
            println("${it.name}'s cards: ${it.cardsInHand.joinToString(", ") { card -> formatCard(card) }} – Total: ${it.total()}")
        }
        println()
    }

    fun showFinalResults(
        dealer: Dealer,
        players: List<Player>,
    ) {
        println("\n## Final Earnings")
        println("Dealer: ${dealer.returnWinningMoneyForDealer(players)}")
        players.forEach {
            println("${it.name}: ${it.returnWinningMoneyForPlayer()}")
        }
    }
}
