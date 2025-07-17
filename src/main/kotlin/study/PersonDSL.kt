package study

// DslMarker tells the compiler to limit the visibility of outer receivers when inside a nested DSL scope, making the DSL safer to use
@DslMarker
annotation class PersonDSL
