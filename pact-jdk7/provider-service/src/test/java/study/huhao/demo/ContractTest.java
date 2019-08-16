package study.huhao.demo;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.RestPactRunner;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

@RunWith(RestPactRunner.class)
@Provider("provider-service-jdk7")
@PactBroker(host = "${pactbroker.host:localhost}", port = "${pactbroker.port:8089}")
public class ContractTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserApi userApi;

    @TestTarget
    public final MockMvcTarget target = new MockMvcTarget();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        target.setControllers(userApi);
    }

    @Test
    @State("an user with id 1 exists")
    public void userExists() {
        when(userRepository.getById(1)).thenReturn(new User(1, "Alex", 10));
    }
}
