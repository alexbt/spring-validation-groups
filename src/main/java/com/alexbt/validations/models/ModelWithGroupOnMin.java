package com.alexbt.validations.models;

import com.alexbt.validations.groups.NotNullGroup;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ModelWithGroupOnMin implements Model {

    @NotNull
    @Min(value = 10, groups = NotNullGroup.class)
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
                + "    @Min(value = 10, groups = NotNullGroup.class)\n"
                + "    private Integer value;\n}\n"
                + "interface NotNullGroup {\n}";
    }
}
