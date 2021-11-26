package org.patsimas.optical_lens_store;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.patsimas.optical_lens_store.dto.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=OrderControllerTest",
        "spring.jmx.default-domain=OrderControllerTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderControllerTest extends BasicWiremockTest {

    @Test
    public void a_save() throws Exception {

        OrderDto orderDto = OrderDto.builder()
//                .id(1L)
                .orderDate(LocalDate.parse("2021-11-30"))
                .deliveryDate(LocalDate.parse("2021-12-03"))
                .total(500.0)
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders
                .post("/orders?contactCardId=2")
                .content(asJsonString(orderDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void b_delete() throws Exception {
        this.mockMvc.perform(delete("/orders/{id}", 2L))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
