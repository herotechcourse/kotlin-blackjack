package blackjack.model

enum class Result(val description: String) {
    WIN("Win"),
    LOSE("Lose"),
    TIE("Tie");

    val inverse: Result
        get() = when (this) {
            WIN -> LOSE
            LOSE -> WIN
            TIE -> TIE
        }
}