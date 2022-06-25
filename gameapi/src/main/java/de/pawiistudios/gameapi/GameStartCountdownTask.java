package de.pawiistudios.gameapi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameStartCountdownTask extends BukkitRunnable {

    private String PREFIX = GameAPI.getPrefix();
    private GameAPI gameManager;

    private static int timeLeft = 30;

    public static void setTimeLeft(int timeLeft) {
        GameStartCountdownTask.timeLeft = timeLeft;
    }

    public GameStartCountdownTask(GameAPI gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void run() {
        timeLeft--;
        if (timeLeft <= 0) {
            cancel();
            gameManager.setGameState(GameState.ACTIVE);
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendTitle(ChatColor.GOLD.toString() + ChatColor.BOLD + "Los gehts!", "", 1, 20, 1);
                all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
            }
            return;
        }
        if (timeLeft == 3) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendTitle(ChatColor.GREEN.toString() + ChatColor.BOLD + "3", "", 1, 20, 1);
                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            }
        }
        if (timeLeft == 2) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendTitle(ChatColor.YELLOW.toString() + ChatColor.BOLD + "2", "", 1, 20, 1);
                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            }
        }
        if (timeLeft == 1) {
            for (Player all : Bukkit.getOnlinePlayers()) {
                all.sendTitle(ChatColor.RED.toString() + ChatColor.BOLD + "1", "", 1, 20, 1);
                all.playSound(all.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
            }
        }
        Bukkit.broadcastMessage(PREFIX + ChatColor.GRAY + "Das Spiel startet in " + ChatColor.YELLOW + timeLeft + ChatColor.GRAY + " Sekunden!");
    }
}
