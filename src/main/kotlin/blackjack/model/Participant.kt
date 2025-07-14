package blackjack.model

abstract class Participant(val name: String) {
    protected val hand = Hand()
    val gameResults: GameResults = GameResults()

    init {
        require(name.isNotBlank()) { "Wrong name: $name. Participant name should not be blank." }
    }

    fun hasBlackJack(): Boolean = hand.hasBlackJack()

    fun isBusts(): Boolean = hand.isBusts()

    fun addCard(newCard: Card) = hand.addCard(newCard)

    fun getScore(): Int = hand.getScore()

    fun setWin() = gameResults.wins++

    fun setLose() = gameResults.loses++

    fun setTie() = gameResults.ties++

}
