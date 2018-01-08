package com.alexbt.validations.controllers;

import com.alexbt.validations.groups.NotNullGroup;
import com.alexbt.validations.groups.NotNullGroupDefault;
import com.alexbt.validations.groups.NullGroup;
import com.alexbt.validations.groups.NullGroupDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.groups.Default;

public abstract class AbstractController<T> {

    @RequestMapping(value = "/NullGroupDefault", method = RequestMethod.POST)
    public ResponseEntity nullGroupDefault(@Validated(NullGroupDefault.class) @RequestBody T model) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/NotNullGroup", method = RequestMethod.POST)
    public ResponseEntity notNullGroup(@Validated(NotNullGroup.class) @RequestBody T model) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/NullGroup", method = RequestMethod.POST)
    public ResponseEntity nullGroup(@Validated(NullGroup.class) @RequestBody T model) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/NotNullGroupDefault", method = RequestMethod.POST)
    public ResponseEntity notNullGroupDefault(@Validated(NotNullGroupDefault.class) @RequestBody T model) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/Default", method = RequestMethod.POST)
    public ResponseEntity defaultGroup(@Validated(Default.class) @RequestBody T model) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity none(@Validated @RequestBody T model) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/NullGroupDefault/Default", method = RequestMethod.POST)
    public ResponseEntity nullGroupDefaultAndDefault(@Validated({NullGroupDefault.class, Default.class}) @RequestBody T model) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/NotNullGroup/Default", method = RequestMethod.POST)
    public ResponseEntity notNullGroupAndDefault(@Validated({NotNullGroup.class, Default.class}) @RequestBody T model) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/NullGroup/Default", method = RequestMethod.POST)
    public ResponseEntity nullGroupAndDefault(@Validated({NullGroup.class, Default.class}) @RequestBody T model) {
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/NotNullGroupDefault/Default", method = RequestMethod.POST)
    public ResponseEntity notNullGroupDefaultAndDefault(@Validated({NotNullGroupDefault.class, Default.class}) @RequestBody T model) {
        return ResponseEntity.ok().build();
    }


}
