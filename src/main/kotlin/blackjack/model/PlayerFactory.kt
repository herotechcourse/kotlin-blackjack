package blackjack.model

object PlayerFactory {
    fun with(names: List<String>): List<Player> {
        return names.map { Player(it) }
    }
}
