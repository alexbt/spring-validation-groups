package com.alexbt.validations.controllers;

import com.alexbt.validations.models.ModelWithGroupOnNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ModelWithGroupOnNull")
public class ModelWithGroupOnNullController extends AbstractController<ModelWithGroupOnNull> {
}
