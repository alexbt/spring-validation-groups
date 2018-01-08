package com.alexbt.validations;

import com.alexbt.validations.controllers.ModelWithGroupDefaultOnMinController;
import com.alexbt.validations.controllers.ModelWithGroupDefaultOnNullController;
import com.alexbt.validations.controllers.ModelWithGroupOnMinController;
import com.alexbt.validations.controllers.ModelWithGroupOnNullController;
import com.alexbt.validations.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author alexbt
 */
@RunWith(SpringRunner.class)
@WebMvcTest({
        ModelWithGroupDefaultOnNullController.class,
        ModelWithGroupDefaultOnMinController.class,
        ModelWithGroupOnNullController.class,
        ModelWithGroupOnMinController.class,
})
public class ValidationGroupsTest {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final Integer INVALID = 1;
    private static final Integer NULL = null;
    private static final LinkedHashMap<Model, List<String>> RESULTS = new LinkedHashMap<>();
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1() throws Exception {
        try {
            test(new ModelWithGroupOnMin(), INVALID, NULL);
            test(new ModelWithGroupOnNull(), INVALID, NULL);
            test(new ModelWithGroupDefaultOnNull(), INVALID, NULL);
            test(new ModelWithGroupDefaultOnMin(), INVALID, NULL);
        } finally {
            FileOutputStream fos = new FileOutputStream("./README.md");
            for (Model model : RESULTS.keySet()) {
                IOUtils.write("```\n" + model + "\n```", fos, Charset.forName("UTF-8"));
                List<String> strings = RESULTS.get(model);

                for (String s : strings) {
                    IOUtils.write("\n- " + s, fos, Charset.forName("UTF-8"));
                }
                IOUtils.write("\n\n---\n\n", fos, Charset.forName("UTF-8"));

            }
        }
    }

    private void write(FileOutputStream fos, String str) throws IOException {
        IOUtils.write(str, fos, Charset.forName("UTF-8"));
    }

    private void test(Model model, Integer... values) throws Exception {
        Class<?>[] minGroups = model.getClass().getDeclaredField("value").getAnnotation(Min.class).groups();
        Class<?>[] notNullGroups = model.getClass().getDeclaredField("value").getAnnotation(NotNull.class).groups();

        String testCase = notNullGroups.length != 0 ? notNullGroups[0].getSimpleName() : minGroups[0].getSimpleName();

        call(model, values, "");
        call(model, values, Default.class.getSimpleName());
        call(model, values, testCase);
        call(model, values, testCase + "/" + Default.class.getSimpleName());
    }

    private void call(Model model, Integer[] values, String str) throws Exception {
        for (Integer value : values) {
            model.setValue(value);
            String uri = "/api/" + model.getClass().getSimpleName() + "/" + str;

            MvcResult mvcResult = mockMvc.perform(post(uri, UUID.randomUUID().toString())
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .content(OBJECT_MAPPER.writeValueAsString(model)))
                    .andReturn();

            printResult(model, value, str, mvcResult);
        }
    }

    private void printResult(Model model, Integer value, String str, MvcResult mvcResult) {
        List<String> strings = RESULTS.computeIfAbsent(model, k -> new LinkedList<>());
        String result = "";

        if (str.contains("/")) {
            str = str.replace("/", ".class, ");
            str += ".class";
        } else if (!str.isEmpty()) {
            str += ".class";
        }
        if (!str.isEmpty()) {
            result += " @Validated(" + str + ")";
        } else {
            result += " @Validated";
        }


        if (mvcResult.getResponse().getStatus() == 200) {
            result += " ignores";
        } else if (mvcResult.getResponse().getStatus() == 400) {
            result += " validates";
        } else {
            fail();
        }

        if (value == null) {
            result += " @NotNull";
        } else {
            result += " @Min";
        }


        strings.add(result);
    }
}
