package blackjack.states

interface Running : State {
    override fun isRunning(): Boolean {
        return true
    }
}
