# Kotlin Blackjack

## Features

### Data class `Card`
- [x] has `Rank`
- [x] has `Suit`

#### enum class `Rank`
- [x] includes names: "ACE"(1), "TWO", "THREE", ... "TEN", "JACK", "QUEEN", "KING"(13)
- [x] includes values: 1-13

#### enum class `Suit`
- [x] includes names: "SPADE", "HEART", "CLUB", "DIAMOND"

### value class `GamblerInfo`
- [x] contains value `name`
  - [x] should not be empty

### data class `PlayerBet`
- [x] Double: `betAmount`
- [x] Double: `winnings`

### abstract class `Player`
- [x] for representing 'Player1', 'Player2', ..., 'PlayerN', and 'Dealer'
- [x] contains class `GamblerInfo`
- [x] contains class `PlayerBet`
- [x] contains list<Card>
- [x] `addCard()` to add card
  - [x] private fun `updateScore` to update score on each insertion
- [x] `isBusted` if score > 21
- [x] `isBlackJack` if score == 21
- [x] `setBetAmount`

### class `Gambler` inherits `Player`
- [x] fun `isPlayerBelowBlackJack`
  - [x] returns true if score is below 21

### class `Dealer` inherits `Player`
- [x] fun `isDealerBelowMinScore`
  - [x] returns true if score is below 16

### class `Deck`
- [x] contains function `generateCards()`
- [x] contains function `shuffle()`
- [x] contains function `Draw()`

### class `FinalResult`
- [x] Calculates the winnings for Player

### directory `view` : object `InputView`, object `OutputView`
#### `InputView`
- [x] fun `getNamesOfPlayers`
- [x] fun `getHitOrStand`
- [x] fun `getBetAmount`
- [x] private fun `readString`

#### `OutputView`
- [x] fun `displayErrorMessages`
- [x] fun `displayNamesOfPlayers`
- [x] fun `displayCardsOfPlayers`
- [x] fun `displayCardsOfDealer`
- [x] fun `displayDealersTurn`
- [x] fun `displayCardsOfPlayersWithScore`
- [x] fun `displayFinalResultsHeading`
- [x] fun `displayFinalEarning`
- [x] fun `displayPlayerResult`
- [x] fun `displayPlayerResult`
- [x] fun `printEmptyLine`
- [x] fun `getCardsOfPlayers`


## Sample Output
```
Enter the names of the players (comma-separated):
player1,player2

Dealing two cards to dealer, player1, player2.
Dealer: K♦
player1's card: 4♠, 6♦
player2's card: Q♦, K♣

Would player1 like to draw another card? (y for yes, n for no)
y
player1's card: 4♠, 6♦, 7♠
Would player1 like to draw another card? (y for yes, n for no)
y
player1's card: 4♠, 6♦, 7♠, 2♦
Would player1 like to draw another card? (y for yes, n for no)
n
Would player2 like to draw another card? (y for yes, n for no)
n
player2's card: Q♦, K♣

dealer's card: K♦, 9♥ – Total: 19
player1's card: 4♠, 6♦, 7♠, 2♦ – Total: 19
player2's card: Q♦, K♣ – Total: 20

## Final Results
Dealer: 0 Win 1 Draw 1 Lose
player2: Win
player1: Lose
```