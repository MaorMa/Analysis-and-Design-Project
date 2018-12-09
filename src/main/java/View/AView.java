package View;

import com.sun.javafx.stage.StageHelper;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;

public abstract class AView {
    protected List<Stage> stages;//  = StageHelper.getStages();//get all stages that exist
    protected static HashMap<String,Scene> scenes = new HashMap<>();//
    Stage mainStage = getStage("Vacation4U");

    /**
     * Closes a stage
     * @param stageName
     */
    public void closeStage(String stageName){
        stages = StageHelper.getStages();//get all stages that exist
        for(int i=0 ;i<stages.size();i++){
            if(stages.get(i).getTitle().equals(stageName))
                stages.get(i).close();
        }
    }

    public Stage getStage(String stageName){
        stages = StageHelper.getStages();
        for(int i=0 ;i<stages.size();i++){
            if(stages.get(i).getTitle().equals(stageName))
                return stages.get(i);
        }
        return null;
    }
}
