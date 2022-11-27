package models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class BaseModel implements Cloneable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Object clone() {
        return null;
    }
}
