package com.example.testcontainers.tcp.mysql

import com.example.testcontainers.TestContainersApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles(profiles = ["tcp"])
@SpringBootTest(classes = [TestContainersApplication.class])
class TestContainersSpringCloudContextTest extends Specification {

  @Autowired
  ApplicationContext applicationContext

  def "Test Containers Playtika context loads"() {
    expect:
    applicationContext
  }
}
