package be4rjp.dadadachecker;

import org.bukkit.scheduler.BukkitRunnable;

public class DADADACheckerTimer extends BukkitRunnable {
    
    private long time = 0;
    
    @Override
    public void run() {
        time++;
    }
    
    
    public long getTime(){return this.time;}
    
    public void setTime(long time){this.time = time;}
}
