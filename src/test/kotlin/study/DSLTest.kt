package study


/*
 * introduce {
 *      name("Jason")
 *      company("DH")
 *      skills {
 *          soft("")
 *          hard("Kotlin")
 *      }
 *      language {
 *          "Korean" level 5
 *          "English" level 3
 *      }
 * }
 */

class DSLTest {
    @Test
    fun name() {
        person  introduce {
            name("Jason")
        }
        assertThat(person.name).isEqualTo("Jason")
    }



    private fun introduce(block: Person.() -> Unit): Person {
        return Person.apply(block)
    }

}