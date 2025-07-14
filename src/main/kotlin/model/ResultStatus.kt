package model

enum class ResultStatus(val value: Int) {
    WIN(1),
    LOSS(2),
    DRAW(3), ;

    companion object {
        fun toText(result: ResultStatus): String {
            return when (result) {
                WIN -> "Win"
                LOSS -> "Lose"
                else -> "Draw"
            }
        }
    }
}
