package com.example;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LastUpdateModel {
    private SimpleStringProperty value = new SimpleStringProperty(this, "value");
    private long savedTime;

    public LastUpdateModel(long val) {
        value.set("Last Update Date: " + val);
        savedTime = val;
    }

    public String getValue() {
        return value.get();
    }

    public long getLong() {
        return savedTime;
    }

    public void setValue(long val) {
        this.value.set("Last Update Date: " + val);
        savedTime = val;
    }

    public StringProperty valueProperty() {
        return value;
    }
}
