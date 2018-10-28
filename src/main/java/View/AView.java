package View;

import com.sun.javafx.stage.StageHelper;
import javafx.stage.Stage;

import java.util.List;

public abstract class AView {
    protected List<Stage> stages;//  = StageHelper.getStages();//get all stages that exist

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
}
