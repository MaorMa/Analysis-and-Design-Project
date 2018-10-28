package View;

import com.sun.javafx.stage.StageHelper;
import javafx.stage.Stage;

import java.util.List;

public abstract class AView {
    protected List<Stage> stages;//  = StageHelper.getStages();//get all stages that exist

    /**
     * Find and return stage by name of stage
     * @param stageName
     * @return
     */
    public Stage getStages(String stageName){
        stages = StageHelper.getStages();//get all stages that exist
        for(int i=0 ;i<stages.size();i++){
            if(stages.get(i).getTitle().equals(stageName))
                return stages.get(i);
        }
        return null;
    }
}
