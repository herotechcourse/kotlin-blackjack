# kotlin-blackjack

## Requirements

- Card values follow standard Blackjack rules:
- Number cards are counted by their face value.
- Face cards (King, Queen, Jack) are each worth 10.
- Aces can be worth either 1 or 11.
- Each player starts with two cards.
- Players may draw additional cards as long as their total remains 21 or less.
- The dealer must draw a card if their total is 16 or less, and must stand on 17 or more.
- If the dealer busts (goes over 21), all remaining players automatically win.
- After the game ends, display the result (win/loss) for each player.

---

## Refactor, step 1

### Holder package

```
CardHoler -> Hand & Deck
```
### CardHolder
CardHolder's child classes will have core logic:
- "draw or receive card/s with own property `List<Card>`"
- and "if `List<Card>` empty but try to draw, `do something`"

- [x] Implement interface/abstract class CardHolder
- [x] Implement `draw`/`receive` logic
- [x] Implement `onDrawFailed()`

### Deck
- [x] has 52 unique cards at `List<Card>`
- [x] Implement `onDrawFailed()`: if failed, generate a new card deck

### Hand
- [x] Implement `onDrawFailed()`: if failed, throw logic error!

---

### Participant package

```
Hand -> Participant -> Player & Dealer -> Participants
```

**Think about this/check later**
- [ ] Currently Participant has a rule that "all `Participant`s" have a "`name`", which is why it was changed to abstract class. Is this decision really necessary?
- [ ] Delete/move methods for tests

#### Participant
- [x] Implement interface/abstract class `Participant` that can separate Player and Dealer
- [x] Implement Parent class as `Participant` interface first, then change if needed
-
#### Player & Dealer
- [x] Implement `Player`
  - [x] has `name`
- [x] Implement `Dealer`
  - [x] has `name` as "Dealer"

#### Participants
- [x] Implement Participants that has List<Participant>
  - [x] Contains Dealer / Player
  - [x] Implement contain/containsAll that can be used as test functions

---