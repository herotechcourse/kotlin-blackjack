package blackjack.model.result

class PlayerResultTracker : ResultTracker {
    override var result: Result? = null

    override fun toString(): String {
        return result?.description ?: "No result recorded."
    }

    override fun record(result: Result) {
        this.result = result
    }
}
