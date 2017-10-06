package producer.integration;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import producer.Application;
import producer.Runner;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes=producer.Application.class)
public class ApplicationTest {

    @MockBean
    private Runner runner;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() throws Exception {
        rabbitTemplate.convertAndSend(Application.queueName, "Hello from RabbitMQ!");
    }
}
