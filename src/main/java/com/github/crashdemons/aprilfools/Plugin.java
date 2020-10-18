/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.aprilfools;

import com.github.crashdemons.playerheads.compatibility.Compatibility;
import com.github.crashdemons.playerheads.compatibility.CompatibleProfile;
import com.github.crashdemons.playerheads.compatibility.CompatibleSkullMaterial;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.SmallFireball;
import org.bukkit.entity.ThrowableProjectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class Plugin extends JavaPlugin implements Listener {
    
    private ItemStack jackolantern = null;
    private ItemStack customskull = null;
    private ItemStack trickortreat = null;
 
    @Override
    public void onEnable(){
        
        //PlayerHeadsAPI phapi = PlayerHeads.getApiInstance();
        jackolantern = Compatibility.getProvider().getCompatibleHeadItem(CompatibleSkullMaterial.PLAYER, 1);
        CompatibleProfile profile = Compatibility.getProvider().createCompatibleProfile(null, UUID.fromString("51d45a7a-34cf-425b-86ab-41f809a1de31"), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjBiZDIwMTI4YzcxMjEwNTA1ZDgwNjJhNTFhZTJhYmUwY2MzZmNhNTAxMDdmODlmMTJkM2E4ZDZkY2ZkYWVhMSJ9fX0=");
        ItemMeta meta = jackolantern.hasItemMeta() ? jackolantern.getItemMeta() : Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        if(meta==null) throw new IllegalStateException("Player Head has no ItemMeta");
        Compatibility.getProvider().setCompatibleProfile(meta, profile);
        jackolantern.setItemMeta(meta);
        
        
        customskull = Compatibility.getProvider().getCompatibleHeadItem(CompatibleSkullMaterial.PLAYER, 1);
        profile = Compatibility.getProvider().createCompatibleProfile(null, UUID.fromString("51d45a7a-34cf-425b-86ab-41f809a1de32"), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmJlY2M2NDVmMTI5YzhiYzJmYWE0ZDgxNDU0ODFmYWIxMWFkMmVlNzU3NDlkNjI4ZGNkOTk5YWE5NGU3In19fQ==");
        meta = customskull.hasItemMeta() ? customskull.getItemMeta() : Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        if(meta==null) throw new IllegalStateException("Player Head has no ItemMeta");
        Compatibility.getProvider().setCompatibleProfile(meta, profile);
        customskull.setItemMeta(meta);
        
        trickortreat = Compatibility.getProvider().getCompatibleHeadItem(CompatibleSkullMaterial.PLAYER, 1);
        profile = Compatibility.getProvider().createCompatibleProfile(null, UUID.fromString("51d45a7a-34cf-425b-86ab-41f809a1de33"), "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGQzMTE3M2U3YjdjNzhlNWU0OGRjZmY2NmQ1Nzk4MGFlY2EzODI0Y2Q4NTg0YWFjNDZkNzdjMjYwYzM4YmI0YyJ9fX0=");
        meta = trickortreat.hasItemMeta() ? trickortreat.getItemMeta() : Bukkit.getItemFactory().getItemMeta(Material.PLAYER_HEAD);
        if(meta==null) throw new IllegalStateException("Player Head has no ItemMeta");
        Compatibility.getProvider().setCompatibleProfile(meta, profile);
        trickortreat.setItemMeta(meta);
        
        
        Bukkit.getPluginManager().registerEvents(this, this);
    }
    
    
    @EventHandler(ignoreCancelled=true)
    public void onProjectile(ProjectileLaunchEvent event){
        if(jackolantern==null){
            getLogger().warning("stack is null");
            return;
        }
        Projectile proj = event.getEntity();
        
        if(proj instanceof LargeFireball){
            ((LargeFireball) proj).setDisplayItem(customskull);
            proj.setFireTicks(0);
        }
        if(proj instanceof SmallFireball){
            ((SmallFireball) proj).setDisplayItem(trickortreat);
            proj.setFireTicks(0);
        }
        
        if(proj instanceof ThrowableProjectile){//thrown items
            //getLogger().info("setting projectile image");
            ThrowableProjectile tproj = (ThrowableProjectile) proj;
            ItemStack stackold = tproj.getItem();
            
            boolean isCustomSnowball = false;
            
            
            
            if(stackold.hasItemMeta()){
                ItemMeta meta = stackold.getItemMeta();
                if(meta.hasLore()){
                    List<String> lore = meta.getLore();
                    
                    for(String line : lore){
                       if( ChatColor.stripColor(line).equalsIgnoreCase("demonic") ){
                           isCustomSnowball = true;
                           break;
                       }
                    }
                    
                }
                
            }
            if(!isCustomSnowball) return;
            
            
            tproj.setFireTicks(3*20);
            
            tproj.setItem(jackolantern);
           
            
            
        }
    }
}
