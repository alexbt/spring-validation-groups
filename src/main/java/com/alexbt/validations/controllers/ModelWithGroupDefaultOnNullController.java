package com.alexbt.validations.controllers;

import com.alexbt.validations.models.ModelWithGroupDefaultOnNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ModelWithGroupDefaultOnNull")
public class ModelWithGroupDefaultOnNullController extends AbstractController<ModelWithGroupDefaultOnNull> {
}
