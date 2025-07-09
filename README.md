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

### View

InputView

- [x] "Enter the names of the players (comma-separated):"
- [x] "Would {name} like to draw another card? (y for yes, n for no)"

### Controller

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