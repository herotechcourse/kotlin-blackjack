# Kotlin Blackjack

## Features: Step1 
### Data class `Card`
- [x] has `Rank`
- [x] has `Suit`
- [x] method `toString()`

#### enum class `Rank`
- [x] includes names: "ACE"(1), "TWO", "THREE", ... "TEN", "JACK", "QUEEN", "KING"(13)
- [x] includes values: 1-13

#### enum class `Suit`
- [x] includes names: "SPADE", "HEART", "CLUB", "DIAMOND"

### class `PlayerCard`
- [x] contain fun `addCard` : updates total score, add element `card`
- [x] Consider Ace as the value of 1 if the total score is going above 21

### value class `GamblerInfo`
- [x] contains value `name`
  - [x] should not be empty
  - [x] can't be 'Dealer' which is already reserved for it (Case Insensitive)

### class `Player`
- [x] contains class `GamblerInfo`
- [x] contains class `hand`
- [x] for representing 'Player1', 'Player2', ..., 'PlayerN', and 'Dealer'

### class `Deck`
- [x] contains function `generateCards()`
- [x] contains function `shuffle()`
- [x] contains function `Draw()`

### directory `view` : object `InputView`, object `OutputView`
#### `InputView`
- [x] fun `get names of the player`
- [x] fun `read String`
- [x] fun `get hit or stand`
#### `OutputView`
- [x] fun `display names of players`
- [x] fun `display the first draw`(parameter: List<Player>)
  - [x] private fun `display message for first draw`(parameter: Player)
  - [x] private fun `display cards and score`

### object `Controller`
- [x] has function `startRound()`
- [x] has function `TakesTurn()`
  - [x] has function `askDrawOrNot()` : until score is greater than 21 or user says 'n'

### class `Statistic`
- [x] will use List<Player> as parameter

### class `Application`
- [x] fun main() to and use `run` from controller

## Updates: Step1
- [x] remove abundant `it` from the line `.map { it -> ...}` in `Controller.kt`
  - [x] of `fun run()`
  - [x] of `private fun playerTakesTurn()`

## Feature-list: Step2 (Updates from Step1)

### Enhanced Blackjack Rules
- [x] Update `Player.isBlackJack()` to check exactly 2 cards with total score 21
- [x] Player loses the entire bet when busting (total > 21)

### Betting System Infrastructure
- [x] Add `betAmount` property to `Player` class
- [x] Update `InputView` to get betting amounts from users
  - [x] Validate: not empty, greater than 0, numeric input
  - [x] Add `getBetAmount(playerName: String): Int` method
- [x] Update `Controller` to handle betting phase after player creation
  - [x] Add `getBetAmounts()` method to collect bets from all players
  - [x] Call betting phase in main game flow

### Payout System
- [x] Update `FinalResult` class for earnings calculations 
  - [] Method to calculate individual player earnings
    - [x] Blackjack winners: when dealer doesn't have Blackjack (1.5x bet)
    - [x] Normal winners: player/dealer both has Blackjack (1x bet)
    - [x] Losses (-1x bet)
    - [x] Ties: player neither wins nor loses (0x bet: push)
- [x] Calculate dealer earnings (negative sum of all player earnings)

### Update Game Results
- [x] Replace `FinalResult` class logic
  - [x] Remove `win`, `draw`, and `lose` lists
  - [x] Add earnings calculation using `Payout` class
- [x] Update `OutputView` to display earnings instead of win/lose
  - [x] Show dealer and player earnings amounts

### Updates: from Code Review
- [x] implement abstract class and inheritance for players and dealer
- [x] refactor `Controller`
  - [x] extract some methods in `Controller` for better readability
- [x] add more `ParameterizedTest` in `GamblerInforTest`

### Implement Next Time
- [] improve `data class Status` by introducing 'State Pattern'
```aiignore
interface State {
    val hand: Hand
    fun draw(card: PlayingCard): State
    fun stay(): State
    fun profit(money: Int): Double
}
```
- [] TDD first and don't forget to add unit tests for `GameOrchestrator`





