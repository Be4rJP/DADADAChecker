package be4rjp.dadadachecker;

import org.bukkit.entity.Player;

public class DADADAData {
    private final Player player;
    
    private long tick = 0;
    private ClickType clickType = ClickType.NO_CLICK;
    
    public DADADAData(Player player){
        this.player = player;
    }
    
    
    public long getTick(){return this.tick;}
    
    public ClickType getClickType(){return this.clickType;}
    
    
    public void setTick(long tick){this.tick = tick;}
    
    public void setClickType(ClickType clickType){this.clickType = clickType;}
    
    //public void addTick(int tick){this.tick += tick;}
}
