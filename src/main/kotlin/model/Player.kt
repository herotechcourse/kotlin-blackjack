package model

import view.InputView

class Player(name: String) : BasePlayer(name) {
    override fun makeDecision(): Boolean {
        val decision = InputView.requestPlayerDecision(name)
        return decision == "y"
    }
}
