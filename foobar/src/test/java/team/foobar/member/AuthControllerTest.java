package team.foobar.member;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import team.foobar.controller.AuthController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void login() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(new Data("test0@gmail.com", "123", false)))
        ).andDo(MockMvcResultHandlers.print());
    }


    static class Data {
        private String email;
        private String pwd;
        private Boolean remember;

        public Data(String email, String pwd, Boolean remember) {
            this.email = email;
            this.pwd = pwd;
            this.remember = remember;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public Boolean getRemember() {
            return remember;
        }

        public void setRemember(Boolean remember) {
            this.remember = remember;
        }
    }

}