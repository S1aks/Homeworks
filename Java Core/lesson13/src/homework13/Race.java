package homework13;

import java.util.Arrays;
import java.util.ArrayList;

public class Race {
    private final ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() { return stages; }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}
