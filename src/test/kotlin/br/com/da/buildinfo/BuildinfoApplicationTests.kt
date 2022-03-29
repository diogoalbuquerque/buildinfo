package br.com.da.buildinfo

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class BuildinfoApplicationTests {

    @Test
    fun contextLoads(context: ApplicationContext) {
        Assertions.assertThat(context).isNotNull
    }
}
