package blackjack.model

import blackjack.view.OutputPrompt

@JvmInline
value class GamblerInfo(val name: String) {
    init {
        require(name.isNotBlank()) { OutputPrompt.INVALID_NAME_EMPTY }
    }
}
