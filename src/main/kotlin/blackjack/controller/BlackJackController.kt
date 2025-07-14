package blackjack.controller

class BlackJackController {
    fun startGame() {
        try {
//            val playersNames = InputView.getPlayersName()
//            val players = ParticipantsFactory.generatePlayers(playersNames)
        } catch (e: Exception) {
            println("Error starting the game: ${e.message}")
        }
    }
}
