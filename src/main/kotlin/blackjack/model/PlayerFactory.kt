package blackjack.model

object PlayerFactory {
    fun fromNames(
        names: List<String>,
        deck: Deck,
    ): List<Player> {
        return names.map { name -> Player(name, deck) }
    }
}
