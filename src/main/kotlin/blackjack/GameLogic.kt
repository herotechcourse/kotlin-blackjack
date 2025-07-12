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

    fun getScores(players: List<Player>, dealer: Dealer): MutableMap<String, Int> {
        val gameScores = mutableMapOf<String, Int>()
        players.map{ player ->
            val sum = player.hand.sumCards()
            gameScores.put(player.toString(), sum)
        }
        val dealerSum = dealer.hand.sumCards()
        gameScores.put(dealer.name, dealerSum)
        return gameScores
    }
}