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
        val cards = dealer.hand.toString()
        println(DISPLAY_HANDS.format(dealer.name, cards))
    }

    fun printPlayersFirstTurnCards (players: List<Player>) {
        players.forEach { player ->
            val cards = player.hand.toString()
            println(DISPLAY_HANDS.format(player.name, cards))
        }
    }

    fun printCurrentCardsOfOnePlayer (player: Player) {
        val cards = player.hand.toString()
        println(DISPLAY_HANDS.format(player.name, cards))
    }
    fun printCurrentDealerCards (dealer: Dealer) {
        val cards = dealer.hand.toString()
        println(DISPLAY_HANDS.format(dealer.name, cards))
    }

    fun printDealerFinalScore (dealer: Dealer) {
        val cards = dealer.hand.toString()
        val score = dealer.sumCards()
        println(DISPLAY_FINAL_HAND.format(dealer.name, cards, score))
    }

    fun printPlayersFinalScore (players: List<Player>) {
        players.forEach { player ->
            val cards = player.hand.toString()
            val score = player.sumCards()
            println(DISPLAY_FINAL_HAND.format(player.name, cards, score))
        }
    }

    fun printFinalPlayerResult (players: List<Player>) {
        players.forEach { player ->
            val result = player.hand.checkWinOrLose()
            println(DISPLAY_FINAL_PLAYER_RESULTS.format(player.name, result))
        }
    }

    fun printFinalDealerResults (dealer: Dealer, players: List<Player>) {
        var countWins = 0
        var countLosses = 0
        players.forEach { player -> if(player.hand.checkWinOrLose() == "Lose") {
            countWins++
        } else { countLosses++ }}
        println()
        println(DISPLAY_TITLE_FINAL_RESULTS)
        println(DISPLAY_FINAL_DEALER_RESULTS.format(dealer.name, countWins, countLosses))
    }

    companion object Messages {
        const val ASK_NAMES = "Enter the names of the players (comma-separated):"
        const val FIRST_TURN_CARDS = "Dealing two cards to dealer, %s."
        const val DISPLAY_HANDS = "%s`s cards: %s"
        const val DISPLAY_FINAL_HAND = "%s`s cards: %s - Total: %s"
        const val DISPLAY_TITLE_FINAL_RESULTS = "## Final Results ##"
        const val DISPLAY_FINAL_PLAYER_RESULTS = "%s: %s"
        const val DISPLAY_FINAL_DEALER_RESULTS = "%s: %s Win %s Lose"
    }
}
