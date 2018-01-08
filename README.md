```
class ModelWithGroupOnMin {
    @NotNull
    @Min(value = 10, groups = NotNullGroup.class)
    private Integer value;
}
interface NotNullGroup {
}
```
-  @Validated ignores @Min
-  @Validated validates @NotNull
-  @Validated(Default.class) ignores @Min
-  @Validated(Default.class) validates @NotNull
-  @Validated(NotNullGroup.class) validates @Min
-  @Validated(NotNullGroup.class) ignores @NotNull
-  @Validated(NotNullGroup.class, Default.class) validates @Min
-  @Validated(NotNullGroup.class, Default.class) validates @NotNull

---

```
class ModelWithGroupOnNull {
    @NotNull(groups = NullGroup.class)
    @Min(10)
    private Integer value;
}
interface NullGroup {
}
```
-  @Validated validates @Min
-  @Validated ignores @NotNull
-  @Validated(Default.class) validates @Min
-  @Validated(Default.class) ignores @NotNull
-  @Validated(NullGroup.class) ignores @Min
-  @Validated(NullGroup.class) validates @NotNull
-  @Validated(NullGroup.class, Default.class) validates @Min
-  @Validated(NullGroup.class, Default.class) validates @NotNull

---

```
class ModelWithGroupDefaultOnNull {
    @NotNull(groups = NullGroupDefault.class)
    @Min(10)
    private Integer value;
}
interface NullGroupDefault extends Default {
}
```
-  @Validated validates @Min
-  @Validated ignores @NotNull
-  @Validated(Default.class) validates @Min
-  @Validated(Default.class) ignores @NotNull
-  @Validated(NullGroupDefault.class) validates @Min
-  @Validated(NullGroupDefault.class) validates @NotNull
-  @Validated(NullGroupDefault.class, Default.class) validates @Min
-  @Validated(NullGroupDefault.class, Default.class) validates @NotNull

---

```
class ModelWithGroupDefaultOnMin {
    @NotNull
    @Min(value = 10, groups = NotNullGroupDefault.class)
    private Integer value;
}
interface NotNullGroupDefault extends Default {
}
```
-  @Validated ignores @Min
-  @Validated validates @NotNull
-  @Validated(Default.class) ignores @Min
-  @Validated(Default.class) validates @NotNull
-  @Validated(NotNullGroupDefault.class) validates @Min
-  @Validated(NotNullGroupDefault.class) validates @NotNull
-  @Validated(NotNullGroupDefault.class, Default.class) validates @Min
-  @Validated(NotNullGroupDefault.class, Default.class) validates @NotNull

---

