package blackjack.model.result

interface ResultTracker {
    fun record(result: Result)
}
