package blackjack.view

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Hand
import blackjack.model.ParticipantWallet
import blackjack.model.Player

class OutputView {
    fun printNameQuestion() {
        println(ASK_NAMES)
    }

    fun printBetQuestion(player: Player) {
        println(ASK_BET.format(player.name))
    }

    fun printPlayersIntroToTheirCards(players: List<Player>) {
        val playersNames = players.map { it.name }        
        println("\n${FIRST_TURN_CARDS.format(playersNames.joinToString(","))}")
    }

    fun formatCard(card: Card): String {
        val cardValue = card.numberToCardDeckElements(card.number)
        return "${cardValue}${card.suit.value}"
    }

    fun formatHand(hand: Hand): String {
        val formattedHand = hand.cards.map { card -> formatCard(card) }
        return formattedHand.joinToString(", ")
    }

    fun printDealerFirstTurnCards(dealer: Dealer) {
        val finalHand = formatHand(dealer.state.hand)
        println(DISPLAY_HANDS.format(dealer.name, finalHand))
    }

    fun printPlayersFirstTurnCards(players: List<Player>) {
        players.forEach { player ->
            val finalHand = formatHand(player.state.hand)
            println(DISPLAY_HANDS.format(player.name, finalHand))
        }
    }

    fun printDealerDrawsOneMoreCardMessage() {
        println("\n${DEALER_DRAW_MESSAGE}")
    }

    fun printCurrentCardsOfOnePlayer(player: Player) {
        val cards = formatHand(player.state.hand)
        println(DISPLAY_HANDS.format(player.name, cards))
    }

    fun printCurrentDealerCards(dealer: Dealer) {
        val cards = formatHand(dealer.state.hand)
        println(DISPLAY_HANDS.format(dealer.name, cards))
    }

    fun printDealerFinalScore(dealer: Dealer) {
        val cards = formatHand(dealer.state.hand)
        val score = dealer.state.hand.sumCards()
        println("\n${DISPLAY_FINAL_HAND.format(dealer.name, cards, score)}")
    }

    fun printPlayersFinalScore(players: List<Player>) {
        players.forEach { player ->
            val cards = formatHand(player.state.hand)
            val score = player.state.hand.sumCards()
            println(DISPLAY_FINAL_HAND.format(player.name, cards, score))
        }
    }

    fun printFinalParticipantResult(participants: MutableMap<String, ParticipantWallet>) {
        println("\n${DISPLAY_TITLE_FINAL_EARNINGS}")
        participants.forEach { participant ->
            println(DISPLAY_FINAL_PLAYER_EARNINGS.format(participant.key, participant.value.earnings.toLong()))
        }
    }

    companion object Messages {
        const val ASK_NAMES = "Enter the names of the players (comma-separated):"
        const val ASK_BET = "Enter %s's betting amount:"
        const val DEALER_DRAW_MESSAGE = "Dealer draws one more card due to having 16 or less."
        const val FIRST_TURN_CARDS = "Dealing two cards to dealer, %s."
        const val DISPLAY_HANDS = "%s`s cards: %s"
        const val DISPLAY_FINAL_HAND = "%s`s cards: %s - Total: %s"
        const val DISPLAY_TITLE_FINAL_EARNINGS = "## Final Earnings ##"
        const val DISPLAY_FINAL_PLAYER_EARNINGS = "%s: %s"
    }
}
