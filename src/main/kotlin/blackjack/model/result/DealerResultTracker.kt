package blackjack.model.result

class DealerResultTracker : ResultTracker {
    private val resultMap =
        mutableMapOf<Result, Int>(
            Result.WIN to 0,
            Result.LOSE to 0,
            Result.TIE to 0,
        )

    override fun record(result: Result) {
        resultMap[result] = resultMap.getValue(result) + 1
    }

    override fun toString(): String {
        return resultMap.entries.joinToString(", ") { "${it.key.description}: ${it.value}" }
    }
}
