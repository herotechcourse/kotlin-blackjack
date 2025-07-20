package blackjack

import kotlin.collections.map

class OutputView {
    fun printNameQuestion () {
        println(ASK_NAMES)
    }

    fun printPlayersIntroToTheirCards (players: List<Player>) {
        val playersNames = players.map { it.name }
        println()
        println(FIRST_TURN_CARDS.format(playersNames.joinToString(",")))
    }

    fun printDealerFirstTurnCards (dealer: Dealer) {
        val cards = formatHand(dealer.hand)
        println(DISPLAY_HANDS.format(dealer.name, cards))
    }

    fun printPlayersFirstTurnCards (players: List<Player>) {
        players.forEach { player ->
            val cards = formatHand(player.hand)
            println(DISPLAY_HANDS.format(player.name, cards))
        }
    }

    fun printDealerDrawsOneMoreCardMessage() {
        println()
        println(DEALER_DRAW_MESSAGE)
    }

    fun printCurrentCardsOfOnePlayer (player: Player) {
        val cards = formatHand(player.hand)
        println(DISPLAY_HANDS.format(player.name, cards))
    }
    fun printCurrentDealerCards (dealer: Dealer) {
        val cards = formatHand(dealer.hand)
        println(DISPLAY_HANDS.format(dealer.name, cards))
    }

    fun printDealerFinalScore (dealer: Dealer) {
        val cards = formatHand(dealer.hand)
        val score = dealer.sumCards()
        println(DISPLAY_FINAL_HAND.format(dealer.name, cards, score))
    }

    fun printPlayersFinalScore (players: List<Player>) {
        players.forEach { player ->
            val cards = formatHand(player.hand)
            val score = player.sumCards()
            println(DISPLAY_FINAL_HAND.format(player.name, cards, score))
        }
    }

    fun printFinalPlayerResult(players: List<Player>, dealer: Dealer) {
        val dealerScore = dealer.sumCards()

        players.forEach { player ->
            val playerScore = player.sumCards()
            val result = player.hand.determineResult(playerScore, dealerScore)
            println(DISPLAY_FINAL_PLAYER_RESULTS.format(player.name, result))
        }
    }

    fun printFinalDealerResults(dealer: Dealer, players: List<Player>) {
        val dealerScore = dealer.sumCards()
        var wins = 0
        var losses = 0

        players.forEach { player ->
            val result = dealer.hand.determineResult(player.sumCards(), dealerScore)
            when (result) {
                "Win" -> losses++
                "Lose" -> wins++
                else -> "Push"
            }
        }

        println()
        println(DISPLAY_TITLE_FINAL_RESULTS)
        println(DISPLAY_FINAL_DEALER_RESULTS.format(dealer.name, wins, losses))
    }

    fun formatHand(hand: Hand): String {
        return hand.cards.joinToString(", ")
    }

    companion object Messages {
        const val ASK_NAMES = "Enter the names of the players (comma-separated):"
        const val DEALER_DRAW_MESSAGE = "Dealer draws one more card due to having 16 or less."
        const val FIRST_TURN_CARDS = "Dealing two cards to dealer, %s."
        const val DISPLAY_HANDS = "%s`s cards: %s"
        const val DISPLAY_FINAL_HAND = "%s`s cards: %s - Total: %s"
        const val DISPLAY_TITLE_FINAL_RESULTS = "## Final Results ##"
        const val DISPLAY_FINAL_PLAYER_RESULTS = "%s: %s"
        const val DISPLAY_FINAL_DEALER_RESULTS = "%s: %s Win %s Lose"
    }
}
