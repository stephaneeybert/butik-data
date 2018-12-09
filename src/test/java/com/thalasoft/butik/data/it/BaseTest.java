package com.thalasoft.butik.data.it;

import com.thalasoft.butik.data.config.DatabaseConfiguration;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = { DatabaseConfiguration.class })
@RunWith(SpringRunner.class)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:mysql/clean-up-before-each-test.sql" })
public abstract class BaseTest {
}
