package blackjack.model.result

class PlayerResultTracker : ResultTracker {
    private var lastResult: Result? = null

    override fun toString(): String {
        return lastResult?.description ?: "No result recorded."
    }

    override fun record(result: Result) {
        lastResult = result
    }
}
