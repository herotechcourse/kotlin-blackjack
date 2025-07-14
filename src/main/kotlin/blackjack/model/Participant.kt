package blackjack.model

abstract class Participant(val name: String) {

    var handState: State = FirstTurn()

    val gameResults: GameResults = GameResults()

    init {
        require(name.isNotBlank()) { "Wrong name: $name. Participant name should not be blank." }
    }


    fun addCard(newCard: Card) {
        handState = handState.draw(newCard)
    }

    fun stay() {
        handState = handState.stay()
    }

    fun isBusts(): Boolean = handState is Bust

    fun hasBlackJack(): Boolean = handState is Blackjack

    fun isFinished(): Boolean = handState is Finished

    fun getScore(): Int = handState.hand.getScore()

    fun setWin() = gameResults.wins++

    fun setLose() = gameResults.loses++

    fun setTie() = gameResults.ties++

    override fun toString(): String {
        return "$name: ${handState.hand}"
    }
}
