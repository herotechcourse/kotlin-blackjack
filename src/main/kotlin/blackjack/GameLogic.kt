package blackjack

class GameLogic {
    fun namesToPlayers(names: List<String>): List<Player> {
        val players = names.map {name -> Player(name)}
        return players
    }

    fun firstTurnCards(players: List<Player>, dealer: Dealer) {
        repeat(2,{
            players.forEach { player ->
                val card = dealer.drawCard()
                player.addCard(card)
            }
        })
        repeat(2, {
            val card = dealer.drawCard()
            dealer.addCard(card)
        })
    }

    fun hasBlackJack(players: List<Player>, dealer: Dealer): Boolean {
        val hand = Hand()
        val results = hand.getScoreResults(players, dealer)
        return when {
            ResultTypes.BLACKJACK in results -> true
            else -> false
        }
    }
}