# kotlin-blackjack

### Reminders for Improvement from Reviewer --@Wordbe

- Use backing properties only when truly necessary.
  - They can make the code harder to read and understand.
- Instead of using numbers to represent states, consider using enums.
  - They make the code much more readable.
- Avoid creating and parsing strings unnecessarily.
- Manage data in structured formats from the beginning—it’ll be easier to maintain.
- Strive to simplify your code as much as possible.
  - Simple code is always easier to manage than complex logic.
- Replace magic numbers with constants.
  - This improves clarity, reduces mistakes, and enhances overall understanding.

## Features List

### Model

PlayingCard

- [x] PlayingCard class hold Enum `Rank` and Enum `Suit`

Hand

- [x] has `cards` which is `List<Card>` for player and dealer
- [x] init `cards` with drawing two cards from `PlayingCard.deck`, if `cards` is empty
- [x] extract logic for calculation of hand's score from `Player` and `Dealer` into `calculateHand()`
- [x] has method `calculateHand()` to calculate score of hand with `Rank` of `Card`
- [x] has method `isBust()` to figure out the hand is bust or not
- [x] has method `isBlackjack()` to figure out the hand is blackjack or not

Rank

- [x] Add new enum class 'Rank'(for example) and use the class to calculate score of player's hand and dealer's hand

Result

- [x] has enum for `LOSE`, `WIN`, `TIE`, and `BLACKJACK` to represent the results of `Playable`

Playable (interface)

- [x] has value `name` in string
- [x] has list of card called `hand`
- [x] has value `bet` in integer
- [x] has value of `result` in Enum class `Result` 
- [x] has method `requestCard()` to request a card by condition then, ask the card manager with boolean
- [x] has method `drawCard()` to take card given by a card manager

Player: Playable

- [x] follows interface `Playable`
- [x] initialize `name`, `bet`, `hand` through constructor

Dealer: Playable

- [x] follows interface `Playable`
- [x] initialize `name`, `hand` through constructor
- [x] has a method `shouldDrawCardOrNot()` return true or false to 'requestCard()' or not

Stats

- [x] has `players: List<Player>`, `dealer: Dealer` having instance of players and dealer
- [x] has private method `calculatePlayerResults()` to calculate result of Players
- [x] has private method `updatePlayerResult()` to update players' result
- [x] has method `calculateEarningMapForPlayable()` to calculate and pay out the earnings for `Playable`s

### View

InputView

- [x] "Enter the names of the players (comma-separated):"
- [x] "Would {name} like to draw another card? (y for yes, n for no)"
- [x] add validation for duplicated names in `readPlayerNames()`
- [x] "Enter {name}’s betting amount:" - to read user betting amount

OutputView

- [x] "Dealing two cards to dealer, {name of players}."
  - [x] display initial state of hands of dealer and players
    - [x] dealer only show first card (`hand[0]`)
    - [x] player show all two cards
- [x] "{name}'s cards: {hand of player}"
  - [x] first hit question, display hand of player (both y or n)
  - [x] from second hit question, display when player say y
- [x] "Dealer draws one more card due to having 16 or less."
  - [x] display the message everytime when dealer get new card
- [x] "{name}'s cards: {hand} – Total: {score}"
- [x] "## Final Earnings"
  - "Dealer: 10000"
  - "{player.name}: 10000"
  - "{player.name}: -20000"

### Controller

GameManager

- [x] refactor `run()` method (It is too big!!!)
