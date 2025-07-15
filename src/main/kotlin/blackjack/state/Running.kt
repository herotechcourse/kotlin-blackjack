package blackjack.state

abstract class Running : State {
    override fun profit(money: Int): Double {
        throw IllegalArgumentException()
    }
}
