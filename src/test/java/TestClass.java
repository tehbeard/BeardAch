import java.util.ArrayList;
import java.util.List;

import me.tehbeard.BeardAch.achievement.Achievement;
import me.tehbeard.BeardAch.achievement.Achievement.Display;
import me.tehbeard.BeardAch.achievement.rewards.DroxTrackReward;
import me.tehbeard.BeardAch.achievement.rewards.IReward;
import me.tehbeard.BeardAch.achievement.triggers.AchCheckTrigger;
import me.tehbeard.BeardAch.achievement.triggers.ITrigger;
import me.tehbeard.BeardAch.dataSource.AchievementLoader;
import me.tehbeard.BeardAch.dataSource.json.RewardJSONParser;
import me.tehbeard.BeardAch.dataSource.json.TriggerJSONParser;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class TestClass {

    
    @Test
    public void t(){
        Achievement a = new Achievement("slug","name","desc",Display.BROADCAST,false);
        
        AchCheckTrigger aa = new AchCheckTrigger();
        aa.configure(a, "ach_chk_slug");
        a.addTrigger(aa);
        
        DroxTrackReward tr = new DroxTrackReward();
        tr.configure(a, "veteran");
        a.addReward(tr);
       
        Gson gson =  new GsonBuilder().
                excludeFieldsWithoutExposeAnnotation().
                registerTypeHierarchyAdapter(ITrigger.class, new TriggerJSONParser()).
                registerTypeHierarchyAdapter(IReward.class, new RewardJSONParser()).
                create();
        
        String json = gson.toJson(a);
        System.out.println(json);
        
        AchievementLoader.triggerFactory.addProduct(AchCheckTrigger.class);
        AchievementLoader.rewardFactory.addProduct(DroxTrackReward.class);
        
        Achievement aaa = gson.fromJson(json, Achievement.class);
        for( ITrigger i : aaa.getTrigs()){
            System.out.println(i.getClass().getName());
        }
        for( IReward i : aaa.getRewards()){
            System.out.println(i.getClass().getName());
        }
        
        
        List<Achievement> alist = new ArrayList<Achievement>();
        alist.add(a);
        alist.add(aaa);
        
        json = gson.toJson(alist);
        System.out.println(json);
        
        List<Achievement> aalist = gson.fromJson(json, new TypeToken<List<Achievement>>(){}.getType());
        System.out.println(aalist.size());
        
    }
}
