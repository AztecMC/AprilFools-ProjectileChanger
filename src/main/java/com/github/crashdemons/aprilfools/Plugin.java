/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.crashdemons.aprilfools;

import org.bukkit.Material;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrowableProjectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author crashdemons (crashenator at gmail.com)
 */
public class Plugin extends JavaPlugin implements Listener {
 
    @Override
    public void onEnable(){
        this.getServer().getPluginManager().registerEvents(this, this);
    }
    
    
    @EventHandler(ignoreCancelled=true)
    public void onProjectile(ProjectileLaunchEvent event){
        Projectile proj = event.getEntity();
        if(proj instanceof ThrowableProjectile){
            ThrowableProjectile tproj = (ThrowableProjectile) proj;
            tproj.setItem(new ItemStack(Material.DIAMOND));
        }
    }
}
