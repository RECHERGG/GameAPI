package de.pawiistudios.gameapi;

import org.bukkit.plugin.java.JavaPlugin;

public class GameAPI extends JavaPlugin {

    private static String prefix;
    private GameState gameState = GameState.LOBBY;
    private GameStartCountdownTask gameStartCountdownTask;
    private final GameAPI game;

    public GameAPI(GameAPI game) {
        this.game = game;
    }

    public static String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        GameAPI.prefix = prefix;
    }

    public void setGameState(GameState gameState) {
        if (this.gameState == GameState.ACTIVE && gameState == GameState.STARTING) return;
        if (this.gameState == gameState) return;

        this.gameState = gameState;

        switch (gameState) {
            case ACTIVE:
                if (this.gameStartCountdownTask != null) this.gameStartCountdownTask.cancel();
                break;
            case STARTING:
                this.gameStartCountdownTask = new GameStartCountdownTask(this);
                this.gameStartCountdownTask.runTaskTimer(game, 0, 20);
                break;
        }
    }
}
