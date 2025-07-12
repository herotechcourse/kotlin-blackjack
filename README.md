# Kotlin-Blackjack

## Description:
A simplified terminal-based Blackjack game written in Kotlin.
The player or dealer with a total closest to 21—without going over—wins.
Includes card dealing, drawing logic, and win/loss/draw result handling.

## Features: 
### Blackjack as Controller:
- [x] Implement a Blackjack class as a controller
- 
### Participants:
- [x] Create Participant abstract class and extend it as Player and Dealer classes
- [x] Implement Card, Deck and DeckGenerator classes

### Dealer
- [ ] Deal card to any participant
- [ ] Dealer auto-draws when score ≤ 16
- [ ] Ask players if they want to draw more cards
- [ ] Check if a participant is busted
- [ ] Calculate participant score with Ace logic
- [ ] Announce game results

### Game
- [ ] Accept player names
- [x] Deal two initial cards to all participants
- [ ] Handle player turns
- [ ] Handle dealer turn
- [ ] Display final hands and scores
- [ ] Determine and print winners using GameLogic.getGameResult()