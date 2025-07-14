package blackjack.model.result

interface ResultTracker {
    var result: Result?
    fun record(result: Result)
}
