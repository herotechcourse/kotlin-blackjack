package blackjack.model.result

class PlayerResultTracker : ResultTracker {
    var lastResult: Result? = null
        private set

    override fun toString(): String {
        return lastResult?.description ?: "No result recorded."
    }

    override fun record(result: Result) {
        lastResult = result
    }
}
