package ru.bellintegrator.practice.person.service;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.bellintegrator.practice.view.organization.OrganizationListFilter;
import ru.bellintegrator.practice.view.organization.OrganizationSaveView;
import ru.bellintegrator.practice.view.organization.OrganizationUpdateView;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void organizations_NoNameParameter_ShouldThrowException() throws Exception {
        OrganizationListFilter organization = new OrganizationListFilter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(organization);
        mockMvc.perform(post("/api/organization/list")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException));
    }


    @Test
    public void organizations_ShouldReturnFoundOrganizationViewList() throws Exception {
        OrganizationListFilter organization = new OrganizationListFilter();
        organization.setName("СБ");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(organization);
        mockMvc.perform(post("/api/organization/list")
                .contentType(APPLICATION_JSON_UTF8)
        .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].name").value("СБ"))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].isActive").value(true));
    }

    @Test
    public void organizationById_ShouldReturnFoundOrganizationView() throws Exception {
        mockMvc.perform(get("/api/organization/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data.name").value("СБ"))
                .andExpect(jsonPath("$.data.fullName").value("СберБанк"))
                .andExpect(jsonPath("$.data.inn").value("123456789012"))
                .andExpect(jsonPath("$.data.address").value("ул. Тверская, 1"))
                .andExpect(jsonPath("$.data.phone").value("8005553535"))
                .andExpect(jsonPath("$.data.kpp").value("123456789"));
    }

    @Test
    public void organizationSaveWithoutRequiredArguments_ShouldReturnError() throws Exception {
        OrganizationSaveView organization = new OrganizationSaveView();
        organization.setName("БР");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(organization);
        mockMvc.perform(post("/api/organization/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException));
    }

    @Test
    public void organizationSaveInvalidKppAndInn_ShouldReturnError() throws Exception {
        OrganizationSaveView organization = new OrganizationSaveView();
        organization.setName("БР");
        organization.setFullName("Банк России");
        organization.setAddress("Тверская");
        organization.setKpp("545354353534");
        organization.setInn("42342342424");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(organization);
        mockMvc.perform(post("/api/organization/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException))
                .andExpect(jsonPath("$.error")
                        .value(allOf(containsString("INN must consist of 12 digits"),
                                containsString("KPP must consist of 9 digits"))
                        ));
    }
    @Test
    public void organizationSave_ShouldReturnResultOK() throws Exception {
        OrganizationSaveView organization = new OrganizationSaveView();
        organization.setName("БР");
        organization.setFullName("Банк России");
        organization.setAddress("Тверская");
        organization.setKpp("123456789");
        organization.setInn("123456789012");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(organization);
        mockMvc.perform(post("/api/organization/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }
    @Test
    public void organizationUpdateWithoutId_ShouldReturnError() throws Exception {
        OrganizationUpdateView organization = new OrganizationUpdateView();
        organization.setName("БР");
        organization.setFullName("Банк России");
        organization.setAddress("Тверская");
        organization.setKpp("123456789");
        organization.setInn("123456789012");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(organization);
        mockMvc.perform(put("/api/organization/update")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException))
                .andExpect(jsonPath("$.error").value("ID value is required"));
    }
    @Test
    public void organizationUpdate_ShouldReturnResultOK() throws Exception {
        OrganizationUpdateView organization = new OrganizationUpdateView();
        organization.setId(1L);
        organization.setName("БР");
        organization.setFullName("Банк России");
        organization.setAddress("Тверская");
        organization.setKpp("123456789");
        organization.setInn("123456789012");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(organization);
        mockMvc.perform(put("/api/organization/update")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }
}