package study.huhao.demo;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactFolder;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonBody;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserContractTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("provider_service", this);

    @Pact(consumer = "user_consumer")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        return builder
                .uponReceiving("get user request")
                .path("/users/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(newJsonBody(o -> {
                    o.numberValue("id", 1);
                    o.stringValue("name", "Alex");
                    o.numberValue("age", 10);
                }).build())
                .toPact();
    }

    @Test
    @PactVerification
    public void runTest() {
        assertEquals(new UserClient(mockProvider.getUrl()).getUserById(1).getName(), "Alex");
    }
}
