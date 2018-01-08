package com.alexbt.validations.models;

import com.alexbt.validations.groups.NullGroupDefault;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ModelWithGroupDefaultOnNull implements Model {

    @NotNull(groups = NullGroupDefault.class)
    @Min(value = 10)
    private Integer value;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "class " + getClass().getSimpleName() + " {\n"
                + "    @NotNull(groups = NullGroupDefault.class)\n"
                + "    @Min(10)\n"
                + "    private Integer value;\n}\n"
                + "interface NullGroupDefault extends Default {\n}";
    }
}
