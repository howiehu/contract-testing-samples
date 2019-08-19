package study.huhao.demo;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringRestPactRunner.class)
@Provider("provider-service")
@PactBroker(host = "${pactbroker.host:localhost}", port = "${pactbroker.port:8089}")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContractTest {

    // We can also use MockMvc to speed up the test's speed to unit test level.
    // https://github.com/DiUS/pact-jvm/blob/master/provider/pact-jvm-provider-spring/README.md#example-of-mockmvc-test

    @MockBean
    private UserRepository userRepository;

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @Test
    @State("an user with id 1 exists")
    public void userExists() {
        reset(userRepository);
        when(userRepository.getById(1)).thenReturn(Optional.of(User.builder().id(1).name("Alex").age(10).build()));
    }
}
