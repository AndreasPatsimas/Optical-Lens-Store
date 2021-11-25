package org.patsimas.optical_lens_store;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.patsimas.optical_lens_store.dto.authenticate.AuthenticationRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=AuthenticationControllerTest",
        "spring.jmx.default-domain=AuthenticationControllerTest"})
public class AuthenticationControllerTest extends BasicWiremockTest {

    private static final String USERNAME = "mario";

    private static final String PASSWORD = "mario";

    private static final String WRONG_PASSWORD = "mar";

    @Test
    public void authenticateSuccess() throws Exception {

        AuthenticationRequest authenticationRequest = AuthenticationRequest
                .builder()
                .username(USERNAME)
                .password(PASSWORD)
                .build();

        this.mockMvc.perform(post("/authenticate")
                .content(asJsonString(authenticationRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void authenticateFailed() throws Exception {

        AuthenticationRequest authenticationRequest = AuthenticationRequest
                .builder()
                .username(USERNAME)
                .password(WRONG_PASSWORD)
                .build();

        this.mockMvc.perform(post("/authenticate")
                .content(asJsonString(authenticationRequest)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }
}
