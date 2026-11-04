package io.github.f1try37.habits.controller;

import io.github.f1try37.habits.config.SecurityConfiguration;
import io.github.f1try37.habits.dto.HabitCreateRequest;
import io.github.f1try37.habits.dto.HabitResponse;
import io.github.f1try37.habits.security.CustomUserDetailsService;
import io.github.f1try37.habits.security.JwtService;
import io.github.f1try37.habits.service.HabitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HabitController.class)
@AutoConfigureMockMvc(addFilters = false)
@WithMockUser(username = "alice")
@Import(SecurityConfiguration.class)
class HabitControllerTest {

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private HabitService habitService;

    @Test
    void shouldCreateHabitAndReturn201() throws Exception {
        //given
        HabitResponse response = new HabitResponse( "Reading", "30 pages", 0);
        when(habitService.create(eq("alice"), any(HabitCreateRequest.class))).thenReturn(response);

        //when + then
        mockMvc.perform(post("/api/habits")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Reading\",\"description\":\"30 pages\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Reading"));
    }

    @Test
    void shouldReturn400WhenNameIsBlank() throws Exception {
        //when + then
        mockMvc.perform(post("/api/habits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"\",\"description\":\"30 pages\"}"))
                .andExpect(status().isBadRequest());
    }
}
