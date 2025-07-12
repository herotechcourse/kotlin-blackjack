package blackjack.model

interface ResultTracker {
    fun record(result: Result)
}