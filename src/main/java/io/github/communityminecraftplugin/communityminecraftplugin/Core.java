package io.github.communityminecraftplugin.communityminecraftplugin;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import io.github.communityminecraftplugin.communityminecraftplugin.command.HelloWorldCommand;
import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Configuration;
import io.github.communityminecraftplugin.communityminecraftplugin.configuration.Settings;
import io.github.communityminecraftplugin.communityminecraftplugin.listener.HelloWorldChatListener;
import io.github.communityminecraftplugin.communityminecraftplugin.listener.JoinListener;

public class Core extends JavaPlugin {
	
	public static final String HELLO_WORLD = "Hello, world!";
	
	@Override
	public void onEnable(){
		preInit();
		postInit();
	}
	
	@Override
	public void onDisable(){
		getLogger().info(HELLO_WORLD.replace("Hello,", "Goodbye,"));
	}
	
	/**
	 * Is called pre-initialisation.
	 * I.e. loading configurations, etc.
	 */
	private void preInit() {
		Settings.registerFromFile(new Configuration(this, new File("settings.yml")));
	}
	
	/**
	 * Is called post-initialisation.
	 * I.e. loading objects that depend on configurations, etc.
	 */
	private void postInit() {
		getLogger().info(HELLO_WORLD);
		getCommand("helloworld").setExecutor(new HelloWorldCommand());
		getServer().getPluginManager().registerEvents(new HelloWorldChatListener(), this);
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
	}
	
}
