package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Gambler
import blackjack.model.Player
import blackjack.model.Rank
import blackjack.model.Suit

object OutputView {
    fun displayErrorMessages(message: String?) {
        println("[Error]::${message ?: ""}")
    }

    fun displayNamesOfPlayers(names: List<String>) {
        val nameList = names.joinToString(", ") { it }
        println("\nDealing two cards to dealer, $nameList.")
    }

    fun displayCardsOfPlayers(players: List<Gambler>) {
        val printableString = players.joinToString("\n") { getCardsOfPlayers(it) }
        println(printableString + "\n")
    }

    fun displayCardsOfPlayers(gambler: Gambler) {
        println(getCardsOfPlayers(gambler))
    }

    fun displayCardsOfDealer(dealer: Dealer) {
        val printableString = "Dealer: ${convertCardToString(dealer.cards[0])}"
        println(printableString)
    }

    fun displayDealersTurn() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayCardsOfPlayersWithScore(players: List<Player>) {
        players.forEach {
            val printableString =
                getCardsOfPlayers(it) + " – Total: ${it.score}"
            println(printableString)
        }
        println()
    }

    fun displayFinalEarning(players: List<Player>) {
        println("\n## Final Earnings")
        players.forEach { println("${it.name}: ${it.winnings.toInt()}") }
    }

    private fun getCardsOfPlayers(player: Player): String {
        return "${player.name}'s card: " +
            player.cards.joinToString(", ", transform = ::convertCardToString)
    }

    private fun convertCardToString(card: Card): String {
        return getRankSymbol(card.rank) + getSuitSymbol(card.suit)
    }

    private fun getSuitSymbol(suit: Suit): String {
        return when (suit) {
            Suit.SPADE -> "♠"
            Suit.DIAMOND -> "♥"
            Suit.CLUB -> "♣"
            Suit.HEART -> "♦"
        }
    }

    private fun getRankSymbol(rank: Rank): String {
        return when (rank) {
            Rank.ACE -> "A"
            Rank.KING -> "K"
            Rank.QUEEN -> "Q"
            Rank.JACK -> "J"
            else -> rank.value.toString()
        }
    }
}
