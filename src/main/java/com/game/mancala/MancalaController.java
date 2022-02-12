package com.game.mancala;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mancala")
public class MancalaController {
    private final MancalaGameService mancalaGame;

    public MancalaController(MancalaGameService mancalaGame) {
        this.mancalaGame = mancalaGame;
    }

    @GetMapping("/game/init")
    GameState initGame() {
        mancalaGame.reset();
        return mancalaGame.getGameBoard();
    }

    @PutMapping("/game/{player}/{pitIndex}")
    GameState updateGame(@PathVariable("player") Player player, @PathVariable("pitIndex") Integer pitIndex) {
        return mancalaGame.pickAndFillPits(pitIndex, player);
    }
}
