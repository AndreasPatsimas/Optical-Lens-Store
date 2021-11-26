package org.patsimas.optical_lens_store;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactCardDto;
import org.patsimas.optical_lens_store.dto.contact_lens.ContactLenDto;
import org.patsimas.optical_lens_store.enums.GlassLenType;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(properties = {"spring.application.name=ContactLenControllerTest",
        "spring.jmx.default-domain=ContactLenControllerTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContactLenControllerTest extends BasicWiremockTest {

    @Test
    public void a_save() throws Exception {

        ContactCardDto glassDto = ContactCardDto.builder()
//                .id(1L)
                .comments("tot")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders
                .post("/contact-lens/{customerId}", 1L)
                .content(asJsonString(glassDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void b_saveContactLen() throws Exception {

        ContactLenDto glassLenDto = ContactLenDto.builder()
                .id(3L)
                .type(GlassLenType.RIGHT)
                .sph("ap")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders
                .put("/contact-lens/lens/{contactCardId}", 2L)
                .content(asJsonString(glassLenDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    public void c_findByCustomerId() throws Exception {
        this.mockMvc.perform(get("/contact-lens/{customerId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void d_delete() throws Exception {
        this.mockMvc.perform(delete("/contact-lens/{id}", 1L))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
