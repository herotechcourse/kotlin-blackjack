package blackjack.model.result

class PlayerResultTracker : ResultTracker {
    var result: Result = Result.UNKNOWN

    override fun toString(): String {
        return result?.description ?: "No result recorded."
    }

    override fun record(result: Result) {
        this.result = result
    }
}
