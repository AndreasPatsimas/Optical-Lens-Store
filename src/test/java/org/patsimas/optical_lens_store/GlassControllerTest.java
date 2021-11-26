package org.patsimas.optical_lens_store;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.patsimas.optical_lens_store.dto.glasses.GlassCategoryDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassLenDto;
import org.patsimas.optical_lens_store.dto.glasses.GlassSkeletonDto;
import org.patsimas.optical_lens_store.enums.GlassCategoryType;
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
@TestPropertySource(properties = {"spring.application.name=GlassControllerTest",
        "spring.jmx.default-domain=GlassControllerTest"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GlassControllerTest extends BasicWiremockTest {

    @Test
    public void a_save() throws Exception {

        GlassDto glassDto = GlassDto.builder()
                .id(5L)
                .comments("tot")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders
                .post("/glasses/{customerId}", 1L)
                .content(asJsonString(glassDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void b_saveCategories() throws Exception {

        GlassCategoryDto glassCategoryDto = GlassCategoryDto.builder()
                .id(13L)
                .type(GlassCategoryType.SMALL)
                .sphOne("teers")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders
                .put("/glasses/categories/{glassId}", 5L)
                .content(asJsonString(glassCategoryDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    public void c_saveGlassLen() throws Exception {

        GlassLenDto glassLenDto = GlassLenDto.builder()
                .id(9L)
                .type(GlassLenType.RIGHT)
                .coating("ap")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders
                .put("/glasses/lens/{glassId}", 5L)
                .content(asJsonString(glassLenDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    public void d_saveGlassSkeleton() throws Exception {

        GlassSkeletonDto glassSkeletonDto = GlassSkeletonDto.builder()
                .id(5L)
                .brand("Aris")
                .build();

        this.mockMvc.perform(RestDocumentationRequestBuilders
                .put("/glasses/skeleton/{glassId}", 5L)
                .content(asJsonString(glassSkeletonDto)).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

    @Test
    public void e_findByCustomerId() throws Exception {
        this.mockMvc.perform(get("/glasses/{customerId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void f_delete() throws Exception {
        this.mockMvc.perform(delete("/glasses/{id}", 4L))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
