package com.leekoko.pipeline;

import java.util.Map;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

public class MemoryPipeline implements Pipeline{

    private Map<String, Object> dataMap;

    public void process(ResultItems resultItems, Task arg1) {
        dataMap = resultItems.getAll();
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
