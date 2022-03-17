package me.barrulik.souledsmp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack exampleSoul;
    public static void init(){
        createRecipes();
    }

    private static void createRecipes(){
        ItemStack soulItem = new ItemStack(Material.RED_DYE, 1);
        ItemMeta soulMeta = soulItem.getItemMeta();
        soulMeta.setDisplayName(ChatColor.DARK_PURPLE+ "Soul");

        List<String> soulLore = new ArrayList<>();
        soulLore.add("Used for making someone your slave");
        soulMeta.setLore(soulLore);
        soulItem.setItemMeta(soulMeta);
        exampleSoul = soulItem;
    }

    public static void setSoulLore(String name) {
        List<String> soulLore = new ArrayList<>();
        soulLore.add("Used for making someone your slave");
        soulLore.add(name + "'s Soul");
        ItemMeta meta = exampleSoul.getItemMeta();
        meta.setLore(soulLore);
        exampleSoul.setItemMeta(meta);
    }
}