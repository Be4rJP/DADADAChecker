# DADADAChecker

プレイヤーの右クリック判定を、　連打・長押し・最初のクリック・クリック無し　のうちどれかで取得できるAPI。　　
ただし連打と長押しに関しては少し判定が甘く、アドベンチャーモード以外での使用はお勧めできません


## How to use

* サーバーのpluginsフォルダにDADADAChecker.jarを入れる

### Maven
※任意の場所にDADADAChecker.jarをいれて＜systemPath＞で指定してください
```xml
<dependencies>
    <dependency>
        <groupId>be4rjp</groupId>
        <artifactId>dadadachecker</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>system</scope>
        <systemPath>${basedir}/lib/DADADAChecker.jar</systemPath>
    </dependency>
</dependencies>
```

### Example of API usage:
###### ExamplePlugin.java
```java
import be4rjp.dadadachecker.DADADACheckerAPI;

public class ExamplePlugin extends JavaPlugin {
    
    private static ExamplePlugin instance;
    private DADADACheckerAPI dadadaCheckerAPI;
    
    @Override
    public void onEnable() {
        instance = this;
        dadadaCheckerAPI = DADADACheckerAPI(instance);
    }
    
    public static ExamplePlugin getInstance() {
        return instance;
    }
    
    public DADADACheckerAPI getDADADACheckerAPI() {
        return dadadaCheckerAPI;
    }

}
```
###### EventListener.java

```java
import be4rjp.dadadachecker.DADADACheckerAPI;
import be4rjp.dadadachecker.ClickType;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerItemClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        if (!event.hasItem()) return;
        
        if (event.getItem().getType() == Material.STICK) {
            //プレイヤーが右クリックしたときに fireClickEvent(Player) を呼び出す
            DADADACheckerAPI dadadaCheckerAPI = ExamplePlugin.getDADADACheckerAPI();
            dadadaCheckerAPI.fireClickEvent(player);
            
            //クリックタイプを取得する
            ClickType clickType = dadadaCheckerAPI.getPlayerClickType(player);
            
            if (clickType == ClickType.RENDA){
                player.sendMessage("あなたは木の棒を連打しています！");
            }
        }
    }
}
```

### ClickType
```yml
NO_CLICK     #クリックをしていないとき
FIRST_CLICK  #前回クリックしたときよりも時間を置いてクリックしたとき
RENDA        #連打しているとき
NAGAOSI      #長押しをしているとき
```

## License
https://github.com/Be4rJP/DADADAChecker/blob/master/LICENSE

## Releases
https://github.com/Be4rJP/DADADAChecker/releases