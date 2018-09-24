package home.samples.device;

import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

import javax.jms.Session;


@Configuration
@EnableJms
public class JmsConfig {



    private final SQSConnectionFactory connectionFactory;


    public JmsConfig() {
        String awsAccessKey = System.getenv("AWS_KEY_ID");
        String awsSecretKey = System.getenv("AWS_SECRET_KEY");
        String awsRegion = System.getenv("AWS_REGION");

        connectionFactory = SQSConnectionFactory.builder()
            .withRegion(Region.getRegion(Regions.fromName(awsRegion)))
            .withAWSCredentialsProvider(new StaticCredentialsProvider(
                new BasicAWSCredentials(awsAccessKey, awsSecretKey)
            ))
            .build();
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory
            = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(this.connectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setConcurrency("3-10");
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(this.connectionFactory);
        return jmsTemplate;
    }


}
