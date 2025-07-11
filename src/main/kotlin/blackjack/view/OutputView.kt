package blackjack.view

import blackjack.model.Card
import blackjack.model.Player
import blackjack.model.Rank
import blackjack.model.Suit

object OutputView {
    fun displayErrorMessages(message: String?) {
        println("[Error]::${message ?: ""}")
    }

    fun displayNamesOfPlayers(players: List<Player>) {
        val nameList = players.joinToString(", ") { it.gamblerInfo.name }
        println("\nDealing two cards to dealer, $nameList.")
    }

    fun displayCardsOfPlayers(player: Player, extraLine: Boolean = false) {
        println(getCardsOfPlayers(player))
        if (extraLine) println()
    }

    fun displayCardsOfDealer(player: Player) {
        val printableString = "Dealer: ${convertCardToString(player.cards[0])}"
        println(printableString)
    }

    fun displayDealersTurn() {
        println("\nDealer draws one more card due to having 16 or less.")
    }

    fun displayCardsOfPlayersWithScore(player: Player, extraLine: Boolean = false) {
        val printableString =
            getCardsOfPlayers(player) + " – Total: ${player.score}"
        println(printableString)
        if (extraLine) println()
    }

    fun displayFinalResultsHeading() {
        println("## Final Results")
    }

    fun displayPlayerResult(
        win: Int,
        lose: Int,
        draw: Int,
    ) {
        println("Dealer: $win Win $draw Draw $lose Lose")
    }

    fun displayPlayerResult(
        name: String,
        result: Boolean,
    ) {
        val printableString = "$name: " + if (result) "Win" else "Lose"
        println(printableString)
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
