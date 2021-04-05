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
import org.springframework.web.bind.MissingServletRequestParameterException;
import ru.bellintegrator.practice.view.user.UserSaveView;
import ru.bellintegrator.practice.view.user.UserUpdateView;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void users_NoNameParameter_ShouldThrowException() throws Exception {
        mockMvc.perform(get("/api/user/list"))
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MissingServletRequestParameterException))
                .andExpect(jsonPath("$.error")
                        .value("Required Long parameter 'officeId' is not present"));
    }


    @Test
    @Order(1)
    public void users_ShouldReturnFoundUserViewList() throws Exception {
        mockMvc.perform(get("/api/user/list").param("officeId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data", hasSize(2)))
                .andExpect(jsonPath("$.data[0].firstName").value("Геннадий"))
                .andExpect(jsonPath("$.data[0].secondName").value("Горин"))
                .andExpect(jsonPath("$.data[0].middleName").value("Геннадиевич"))
                .andExpect(jsonPath("$.data[0].position").value("Менеджер"));
    }

    @Test
    @Order(2)
    public void userById_ShouldReturnFoundUserView() throws Exception {
        mockMvc.perform(get("/api/user/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.data.firstName").value("Геннадий"))
                .andExpect(jsonPath("$.data.secondName").value("Горин"))
                .andExpect(jsonPath("$.data.middleName").value("Геннадиевич"))
                .andExpect(jsonPath("$.data.phone").value("9995553535"))
                .andExpect(jsonPath("$.data.citizenshipName").value("Российская Федерация"))
                .andExpect(jsonPath("$.data.citizenshipCode").value("634"))
                .andExpect(jsonPath("$.data.docName").value("Паспорт иностранного гражданина"))
                .andExpect(jsonPath("$.data.docNumber").value("555555555555"))
                .andExpect(jsonPath("$.data.position").value("Менеджер"));
    }

    @Test
    public void userSaveWithoutRequiredArguments_ShouldReturnError() throws Exception {
        UserSaveView user = new UserSaveView();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/api/user/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException))
                .andExpect(jsonPath("$.error")
                        .value(allOf(
                                containsString("Firstname value is required"),
                                containsString("OfficeId value is required"),
                                containsString("Position value is required"))
                        ));
    }

    @Test
    public void userSave_ShouldReturnResultOK() throws Exception {
        UserSaveView user = new UserSaveView();
        user.setFirstName("Пётр");
        user.setOfficeId(1L);
        user.setPosition("Строитель");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);
        mockMvc.perform(post("/api/user/save")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }
    @Test
    public void userUpdateWithoutId_ShouldReturnError() throws Exception {
        UserUpdateView user = new UserUpdateView();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);
        mockMvc.perform(put("/api/user/update")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(result -> assertTrue(result.getResolvedException()
                        instanceof MethodArgumentNotValidException))
                .andExpect(jsonPath("$.error")
                        .value(allOf(
                                containsString("Firstname value is required"),
                                containsString("ID value is required"),
                                containsString("Position value is required"))
                        ));
    }
    @Test
    public void userUpdate_ShouldReturnResultOK() throws Exception {
        UserUpdateView user = new UserUpdateView();
        user.setId(1L);
        user.setFirstName("Игорь");
        user.setPosition("Медицинский представитель");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(user);
        mockMvc.perform(put("/api/user/update")
                .contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("success"));
    }
}