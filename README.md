# kotlin-blackjack

## Features List

### Model

Card

- [x] Card class hold string value of name(e.g. "A♥") and string value of digit(e.g. "A", "5", "K")

[//]: # (Hand)

[//]: # (- [ ] add method to link 'Card' and 'Rank' to calculate score of a hand)

Rank

- [x] Add new enum class 'Rank'(for example) and use the class to calculate score of player's hand and dealer's hand

Playable (interface)

- [x] has value `name` in string
- [x] has list of card called `hand`
- [x] has method `requestCard()` to request a card by condition then, ask the card manager with boolean
- [x] has method `drawCard()` to take card given by a card manager
- [x] has method `calculateHand()` to calculate score of hand
- [x] has method `isBust()` to figure out the player of the dealer is bust or not

Player: Playable

- [x] follows interface `Playable`
- [x] has method `calculateHand()` to calculate score of hand with `Rank` of `Card`
    - TODO: MUST have to find a solid logic for calculation

Dealer: Playable

- [x] follows interface `Playable`
- [x] has a method `shouldDrawCardOrNot()` return true or false to 'requestCard()' or not

Stats

- [x] has `players: List<Player>`, `dealer: Dealer` having instance of players and dealer
- [x] has `playerBoard: Map<Player, Int>` having key as player and value as result of player
- [x] has `dealerStats: Map<String, Int>` having key as "win", "lose", "tie" and value as count
- [x] has method `initPlayerBoard()` to initiate `playerBoard` based on the result of each player against dealer
- [x] hsa method `updateDealerStats()` to update `dealerStats` calculation based on `playerBoard`
- [ ] has method `compareDealerPlayer()` to compare win or lose or tie between dealer and player

### View

InputView

- [x] "Enter the names of the players (comma-separated):"
- [x] "Would {name} like to draw another card? (y for yes, n for no)"
- [ ] add validation for duplicated names in `readPlayerNames()`

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
- [ ] "## Final Results"
    - "Dealer: 1 Win 1 Lose"
    - "{player.name}: Win"
    - "{player.name}: Lose"

### Controller

GameManager

- [x] has 'players: List<player>' to store all players -> extract to 'PlayerManager'
- [x] has 'dealer: Dealer' -> extract to 'DealerManager'
- [ ] has 'winStatistics: Stats' to store data related with result of the game
- [x] has link with 'InputView' to take user inputs
- [ ] has link with 'OutputView' to output game result
- [ ] separate responsibilities of GameManager into several new manager(controller) classes
- [ ] refactor `run()` method (It is too big!!!)

CardManager

- [x] has 'cards: List<Card>' and get it from 'CardGenerator' as an input parameter
- [x] has 'cardMap: Map<Card, Boolean>' to figure out a card is available or not
- [x] giveCard method return Card from shuffled 'cards', then check the card to 'false' in 'cardMap'
- [x] use ArrayDeque to take a card and remove the card from the deque and update '_cards'

### Utils

CardGenerator

- [x] This is an object
- [x] generateSuits method create list of cards in String form by combining digit part and figure part
- [x] generateCard method create card with combination of 'A 1 2 ... 10 J Q K' * ♥, ♣, ♠, ♦
    - ex. A♥, 4♣, 9♠, 6♦
- [x] generateCards method create 52 unique cards