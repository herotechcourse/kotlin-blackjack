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

Card

- [x] Card class hold string value of name(e.g. "A♥") and string value of digit(e.g. "A", "5", "K")

Hand

- [x] has `List<Card>` for player and dealer
- [x] extract logic for calculation of hand's score from `Player` and `Dealer` into `calculateCards()`
- [x] has method `calculateHand()` to calculate score of hand with `Rank` of `Card`

Rank

- [x] Add new enum class 'Rank'(for example) and use the class to calculate score of player's hand and dealer's hand

Result

- [x] has enum for `LOSE`, `WIN`, `TIE`, and `BLACKJACK` to represent the results of `Playable`

Playable (interface)

- [x] has value `name` in string
- [x] has list of card called `hand`
- [x] has value `bet` in integer
- [x] has method `requestCard()` to request a card by condition then, ask the card manager with boolean
- [x] has method `drawCard()` to take card given by a card manager
- [x] has method `calculateHand()` to calculate score of hand
- [x] has method `isBust()` to figure out the player of the dealer is bust or not
- [x] has method `isBlackjack()` to figure out the `Playable` is blackjack or not

Player: Playable

- [x] follows interface `Playable`
- [x] has method `placeBets()` to place `bet`

Dealer: Playable

- [x] follows interface `Playable`
- [x] has a method `shouldDrawCardOrNot()` return true or false to 'requestCard()' or not

Stats

- [x] has `players: List<Player>`, `dealer: Dealer` having instance of players and dealer
- [x] has `dealerStats: Map<String, Int>` having key as "win", "lose", "tie" and value as count
- [x] has method `payOutPotToEarnings()` to calculate and pay out the earnings for `Playable`s

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

- [x] has 'players: List<player>' to store all players -> extract to 'PlayerManager'
- [x] has 'dealer: Dealer' -> extract to 'DealerManager'
- [x] has 'winStatistics: Stats' to store data related with result of the game
- [x] has link with 'InputView' to take user inputs
- [x] has link with 'OutputView' to output game result
- [x] separate responsibilities of GameManager into several new manager(controller) classes
- [x] refactor `run()` method (It is too big!!!)
