package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Hand
import blackjack.model.Player
import blackjack.model.PlayingCard
import blackjack.model.Stats

object OutputView {
    fun displayInitialState(
        players: List<Player>,
        dealer: Dealer,
    ) {
        var sentence = "dealer, "
        val names = players.map { it.name }
        sentence += names.joinToString(", ")
        println("\nDealing two cards to $sentence.\nDealer: ${cardToText(dealer.hand.cards[0])}")
        players.forEach { displayCurrentHand(it) }
    }

    fun displayCurrentHand(player: Player) {
        val hand = player.hand
        println("${player.name}'s cards: ${handToText(hand)}")
    }

    fun displayDealerDrawsCard() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayFinalState(
        players: List<Player>,
        dealer: Dealer,
    ) {
        println("\nDealer's cards: ${handToText(dealer.hand)} – Total: ${dealer.hand.calculateHand()}")
        players.forEach { player ->
            println("${player.name}'s cards: ${handToText(player.hand)} – Total: ${player.hand.calculateHand()}")
        }
    }

    fun displayEarnings(winStatistics: Stats) {
        val players = winStatistics.players
        val dealer = winStatistics.dealer
        val earningsMap = winStatistics.calculateEarningMapForPlayable()
        println("\n## Final Earnings\nDealer: ${earningsMap[dealer]}")
        players.forEach { player ->
            println("${player.name}: ${earningsMap[player]}")
        }
    }

    private fun handToText(hand: Hand): String {
        return hand.cards.joinToString(", ") { cardToText(it) }
    }

    private fun cardToText(card: PlayingCard): String {
        return card.rank.digit + card.suit.form
    }
}
