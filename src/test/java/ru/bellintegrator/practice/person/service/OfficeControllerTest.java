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
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.bellintegrator.practice.view.office.OfficeSaveView;
import ru.bellintegrator.practice.view.office.OfficeUpdateView;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class OfficeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    public void officesWithoutArguments_ShouldReturnFoundAllOfficeViewList() throws Exception {
        mockMvc.perform(get("/api/office/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data", hasSize(3)))
                .andExpect(jsonPath("$.data[0].name").value("Рассвет"))
                .andExpect(jsonPath("$.data[0].id").value(1L))
                .andExpect(jsonPath("$.data[0].isActive").value(true));
    }

    @Test
    public void officesWithArguments_ShouldReturnFoundOfficeViewList() throws Exception {
        mockMvc.perform(get("/api/office/list")
                .param("name", "Восход"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data", hasSize(1)))
                .andExpect(jsonPath("$.data[0].name").value("Восход"))
                .andExpect(jsonPath("$.data[0].id").value(2L))
                .andExpect(jsonPath("$.data[0].isActive").value(true));
    }

    @Test
    public void officeById_ShouldReturnFoundOfficeView() throws Exception {
        mockMvc.perform(get("/api/office/{id}", 3L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data.name").value("Мир"))
                .andExpect(jsonPath("$.data.id").value(3L))
                .andExpect(jsonPath("$.data.address").value("б. Новинский, 32"))
                .andExpect(jsonPath("$.data.phone").value("4990835628"))
                .andExpect(jsonPath("$.data.isActive").value(true));
    }

    @Test
    public void officeSaveWithoutRequiredArguments_ShouldReturnError() throws Exception {
        OfficeSaveView office = new OfficeSaveView();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(office);
        mockMvc.perform(post("/api/office/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException))
        .andExpect(jsonPath("$.error").value("OrgId value is required"));
    }

    @Test
    public void officeSave_ShouldReturnResultOK() throws Exception {
        OfficeSaveView office = new OfficeSaveView();
        office.setOrgId(1L);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(office);
        mockMvc.perform(post("/api/office/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }

    @Test
    public void officeUpdateWithoutArguments_ShouldReturnError() throws Exception {
        OfficeUpdateView office = new OfficeUpdateView();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(office);
        mockMvc.perform(put("/api/office/update")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException))
                .andExpect(jsonPath("$.error")
                        .value(allOf(containsString("Address value is required"),
                                containsString("ID value is required"),
                                containsString("Name value is required"))
                                ));
    }
    @Test
    public void officeUpdate_ShouldReturnResultOK() throws Exception {
        OfficeUpdateView office = new OfficeUpdateView();
        office.setId(1L);
        office.setName("Звезда");
        office.setAddress("Пресненский вал, 4");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(office);
        mockMvc.perform(put("/api/office/update")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }
}