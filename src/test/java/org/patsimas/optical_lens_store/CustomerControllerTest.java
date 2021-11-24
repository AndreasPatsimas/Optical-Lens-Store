package org.patsimas.optical_lens_store;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.patsimas.optical_lens_store.dto.CustomerDto;
import org.patsimas.optical_lens_store.dto.CustomerSearchRequestDto;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=CustomerControllerTest",
        "spring.jmx.default-domain=CustomerControllerTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerControllerTest extends BasicWiremockTest {

    @Test
    public void a_advancedSearch() throws Exception {

        CustomerSearchRequestDto searchRequestDto = CustomerSearchRequestDto.builder()
//                .firstName("Αντ")
//                .lastName("Πατσ")
                .phone("6988116719")
                .email("andreas-patsim@hotmail.com")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders
                .post("/customers/advanced-search?page=0&pageSize=10")
//                .header(HEADER_KEY, HEADER_VALUE)
                .content(asJsonString(searchRequestDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void c_save() throws Exception {

        CustomerDto customerDto = CustomerDto.builder()
                .id(1L)
                .firstName("Αντρέας")
                .lastName("Πατσιμάς")
                .phone("6988116719")
                .email("andreas-patsim@hotmail.com")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders
                .post("/customers")
//                .header(HEADER_KEY, HEADER_VALUE)
                .content(asJsonString(customerDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void b_delete() throws Exception {
        this.mockMvc.perform(delete("/customers/{id}",
                1L)
//                .header(HEADER_KEY, HEADER_VALUE)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
