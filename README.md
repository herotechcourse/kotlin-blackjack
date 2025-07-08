# kotlin-blackjack

## Features List

### Model

Card
- [x] Card class hold string value of name(e.g. "A♥") and string value of digit(e.g. "A", "5", "K")

Hand
- [ ] add method to link 'Card' and 'Rank' to calculate score of a hand

Rank
- [x] Add new enum class 'Rank'(for example) and use the class to calculate score of player's hand and dealer's hand
  


### View 

InputView
- [x] "Enter the names of the players (comma-separated):"
- [x] "Would {name} like to draw another card? (y for yes, n for no)"


### Controller

CardManager
- [ ] has 'cards: List<Card>' and get it from 'CardGenerator' as a input parameter
- [ ] has 'cardMap: Map<Card, Boolean>' to figure out a card is used or not
- [ ] giveCard method return random selected Card from 'cards', then check the card to 'false' in 'cardMap'
- [ ] if giveCard choose a card that is 'false' in 'cardMap', recall giveCard method until it return 'true' card 
  - e.g. use 'repeat()'


### Utils

CardGenerator
- [x] This is an object
- [x] generateSuits method create list of cards in String form by combining digit part and figure part
- [x] generateCard method create card with combination of 'A 1 2 ... 10 J Q K' * ♥, ♣, ♠, ♦ 
  - ex. A♥, 4♣, 9♠, 6♦
- [x] generateCards method create 52 unique cards