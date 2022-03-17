package me.barrulik.souledsmp;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public final class SouledSMP extends JavaPlugin implements Listener {
    SouledSMP instance;
    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);

        System.out.println("Souled smp by Barrulik");
        ItemManager.init();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player dead = event.getEntity();
        Entity killer = event.getEntity().getKiller();
        if (killer instanceof Player) {
            ItemManager.setSoulLore(dead.getName());
            ItemStack soul = ItemManager.exampleSoul;
            dead.getWorld().dropItem(dead.getLocation(), soul);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rasputin")) {
            Player p = (Player) sender;
            if (p != null)
                p.sendMessage(ChatColor.AQUA + "Hey, hey, hey, hey, hey, hey, hey, hey\n" +
                        "Hey, hey, hey, hey, hey, hey, hey, hey\n" +
                        "Hey, hey, hey, hey, hey, hey, hey, hey\n\n" +
                        "There lived a certain man in Russia long ago\n" +
                        "He was big and strong, in his eyes a flaming glow\n" +
                        "Most people looked at him with terror and with fear\n" +
                        "But to Moscow chicks he was such a lovely dear\n" +
                        "He could preach the Bible like a preacher\n" +
                        "Full of ecstasy and fire\n" +
                        "But he also was the kind of teacher\n" +
                        "Women would desire\n\n" +
                        "Ra ra Rasputin\n" +
                        "Lover of the Russian queen\n" +
                        "There was a cat that really was gone\n" +
                        "Ra ra Rasputin\n" +
                        "Russia's greatest love machine\n" +
                        "It was a shame how he carried on\n\n" +
                        "He ruled the Russian land and never mind the Czar\n" +
                        "But the kazachok he danced really wunderbar\n" +
                        "In all affairs of state he was the man to please\n" +
                        "But he was real great when he had a girl to squeeze\n" +
                        "For the queen he was no wheeler dealer\n" +
                        "Though she'd heard the things he'd done\n" +
                        "She believed he was a holy healer\n" +
                        "Who would heal her son\n\n" +
                        "Ra ra Rasputin\n" +
                        "Lover of the Russian queen\n" +
                        "There was a cat that really was gone\n" +
                        "Ra ra Rasputin\n" +
                        "Russia's greatest love machine\n" +
                        "It was a shame how he carried on\n\n" +
                        "But when his drinking and lusting\n" +
                        "And his hunger for power\n" +
                        "Became known to more and more people\n" +
                        "The demands to do something\n" +
                        "About this outrageous man\n" +
                        "Became louder and louder\n\n" +
                        "Hey, hey, hey, hey, hey, hey, hey, hey\n" +
                        "Hey, hey, hey, hey, hey, hey, hey, hey\n" +
                        "Hey, hey, hey, hey, hey, hey, hey, hey\n" +
                        "Hey, hey, hey, hey, hey, hey, hey, hey\n\n" +
                        "\"This man's just got to go\", declared his enemies\n" +
                        "But the ladies begged, \"Don't you try to do it, please\"\n" +
                        "No doubt this Rasputin had lots of hidden charms\n" +
                        "Though he was a brute, they just fell into his arms\n" +
                        "Then one night some men of higher standing\n" +
                        "Set a trap, they're not to blame\n" +
                        "\"Come to visit us\", they kept demanding\n" +
                        "And he really came\n\n" +
                        "Ra ra Rasputin\n" +
                        "Lover of the Russian queen\n" +
                        "They put some poison into his wine\n" +
                        "Ra ra Rasputin\n" +
                        "Russia's greatest love machine\n" +
                        "He drank it all and said, \"I feel fine\"\n\n" +
                        "Ra ra Rasputin\n" +
                        "Lover of the Russian queen\n" +
                        "They didn't quit, they wanted his head\n" +
                        "Ra ra Rasputin\n" +
                        "Russia's greatest love machine\n" +
                        "And so they shot him 'til he was dead");
        }
        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (event.getItem() != null) {
                if (event.getItem().getItemMeta().getLore().get(0).equals(ItemManager.exampleSoul.getItemMeta().getLore().get(0))) {
                    Player p = event.getPlayer();
                    String soulOwnerName = getSoulOwner(event.getItem());
                    if (soulOwnerName.equals(p.getName())){
                        p.sendMessage(ChatColor.AQUA + "Soul restored successfully");
                        event.getItem().setAmount(event.getItem().getAmount() - 1);
                    } else {
                        Player soulOwner = getServer().getPlayer(soulOwnerName);
                        if (soulOwner == null){
                            p.sendMessage(ChatColor.RED + "Hi, it wasn't possible to teleport to him because he is not online");
                        } else {
                            p.sendMessage(ChatColor.AQUA + "Teleporting");
                            p.teleport(soulOwner);
                        }
                    }
                }
            }
        }
    }

    public static String getSoulOwner(ItemStack soul){
        return soul.getItemMeta().getLore().get(1).substring(0,"'s Soul".length()+1);
    }
}

