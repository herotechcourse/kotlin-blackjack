package blackjack.model.result

enum class Result(val description: String) {
    WIN("Win"),
    LOSE("Lose"),
    TIE("Tie"),
    BLACKJACK("Win"),
    UNKNOWN("Unknown"),
    ;

    val inverse: Result
        get() =
            when (this) {
                WIN -> LOSE
                LOSE -> WIN
                TIE -> TIE
                BLACKJACK -> LOSE
                UNKNOWN -> UNKNOWN
            }
}
