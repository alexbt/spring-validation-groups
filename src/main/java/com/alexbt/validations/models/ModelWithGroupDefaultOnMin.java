package com.alexbt.validations.models;

import com.alexbt.validations.groups.NotNullGroupDefault;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ModelWithGroupDefaultOnMin implements Model {

    @NotNull
    @Min(value = 10, groups = NotNullGroupDefault.class)
    private Integer value;

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "class " + getClass().getSimpleName() + " {\n"
                + "    @NotNull\n"
                + "    @Min(value = 10, groups = NotNullGroupDefault.class)\n"
                + "    private Integer value;\n}\n"
                + "interface NotNullGroupDefault extends Default {\n}";
    }
}
