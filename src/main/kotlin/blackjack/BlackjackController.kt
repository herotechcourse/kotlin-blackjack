package blackjack

object BlackjackController {
    fun run() {
//        val c1 = Card(CardSuit.CLUB, 2)
//        val c2 = Card(CardSuit.HEART, 11)
//        val hand = Hand()
//        hand.addCard(c1)
//        hand.addCard(c2)
//        println(hand.toString())
        // __________________________

        val game = GameLogic()
        val deck = Deck.generate()
        val dealer = Dealer(deck)
        val input = InputView()
        val output = OutputView()


        val playersNames = input.readPlayersName()
        val players = game.namesToPlayers(playersNames)
        output.printPlayersIntroToTheirCards(players)
        game.firstTurnCards(players, dealer)
        output.printFirstTurnCards(dealer)
        players.forEach { player ->
            output.printFirstTurnCards(player)
//            val playerStatus = player.hand.checkWinOrLose()
        }

        game.otherTurnCards(players, dealer)

//         display second hand of each participant

//         check hand status


//        if (gameBlackjackCheck == true) {
//            // print results
//
//        } else {
//            game.shouldHit(players, dealer)
//            // remove busted players
//            // ask for hit or stay
//        }

    }
}