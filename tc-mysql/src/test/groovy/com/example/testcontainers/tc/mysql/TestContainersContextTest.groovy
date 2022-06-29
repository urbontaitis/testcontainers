package com.example.testcontainers.tc.mysql

import com.example.testcontainers.TestContainersApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles(profiles = ["tc"])
@SpringBootTest(classes = [TestContainersApplication.class])
class TestContainersContextTest extends Specification {

  @Autowired
  ApplicationContext applicationContext

  def "Testcontainers module context loads"() {
    expect:
    applicationContext
  }

}
