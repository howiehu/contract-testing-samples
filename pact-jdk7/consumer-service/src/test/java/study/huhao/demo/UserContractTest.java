package study.huhao.demo;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserContractTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("provider-service-jdk7", this);

    @Pact(consumer = "consumer-service-jdk7")
    public RequestResponsePact createFragment(PactDslWithProvider builder) {
        return builder
                .given("an user with id 1 exists")
                .uponReceiving("a request for an user")
                .path("/users/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(new PactDslJsonBody()
                        .numberValue("id", 1)
                        .stringValue("name", "Alex")
                        .numberValue("age", 10))
                .toPact();
    }

    @Test
    @PactVerification
    public void runTest() {
        User user = new UserClient(mockProvider.getUrl()).getUserById(1);
        assertEquals(user.getId(), 1);
        assertEquals(user.getName(), "Alex");
        assertEquals(user.getAge(), 10);
    }
}
