package project.model.extjs;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExtData  {

    @JsonProperty("data")
    private final List<Object> data = new ArrayList<>();
    private boolean success;

    public void add(Object item) {

        if(item == null) return;

        if(item instanceof Collection) {
            data.addAll((Collection) item);
        } else {
            data.add(item);
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
