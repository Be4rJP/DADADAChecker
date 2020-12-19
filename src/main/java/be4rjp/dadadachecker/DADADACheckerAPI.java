package be4rjp.dadadachecker;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class DADADACheckerAPI {
    
    private final JavaPlugin plugin;
    private final DADADACheckerTimer timer;
    
    private Map<Player, DADADAData> dataMap;
    
    /**
     * @param plugin JavaPlugin that tasks will be allocated on.
     */
    public DADADACheckerAPI(JavaPlugin plugin){
        this.plugin = plugin;
        this.timer = new DADADACheckerTimer();
        this.timer.runTaskTimer(plugin, 0, 1);
        this.dataMap = new HashMap<>();
    }
    
    
    /**
     * Call this method once before checking the player's click.
     *
     * @param player Player you want to register.
     */
    public void registerPlayer(Player player){
        if(!dataMap.containsKey(player)){
            DADADAData data = new DADADAData(player);
            dataMap.put(player, data);
        }
    }
    
    
    /**
     * Call this method when a player right-clicks.
     *
     * @param player Player who right-clicks.
     */
    public void fireClickEvent(Player player){
        if(!dataMap.containsKey(player)) {
            registerPlayer(player);
            return;
        }
        
        DADADAData data = dataMap.get(player);
        long tick = this.timer.getTime() - data.getTick();
        
        if(tick < 4){
            data.setClickType(ClickType.RENDA);
        }else{
            if(tick <= 9)
                data.setClickType(ClickType.NAGAOSI);
            else
                data.setClickType(ClickType.FIRST_CLICK);
        }
        
        data.setTick(this.timer.getTime());
    }
    
    
    /**
     * @param player Player you want to get whose click type.
     * @return Player's click type.
     */
    public ClickType getPlayerClickType(Player player){
        if(!dataMap.containsKey(player)) {
            registerPlayer(player);
            return ClickType.NO_CLICK;
        }
    
        DADADAData data = dataMap.get(player);
        long tick = this.timer.getTime() - data.getTick();
        
        if(tick >= 9){
            data.setClickType(ClickType.NO_CLICK);
            return ClickType.NO_CLICK;
        }else{
            return data.getClickType();
        }
    }
    
}
