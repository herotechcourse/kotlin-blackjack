package blackjack

class GameLogic {
    fun namesToPlayers(names: List<String>): List<Player> {
        val players = names.map {name -> Player(name)}
        return players
    }

}