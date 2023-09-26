package com.workoutbuilder.enterprise;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class TestProtectedEndpoints {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "mockuser@testing.com")
    public void userCanAccessTheirOwnDashboard() throws Exception {
        this.mockMvc.perform(get("/3/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard"));
    }

    @Test
    @WithMockUser(username = "mockuser@testing.com")
    public void userCanAccessTheirOwnSettings() throws Exception {
        this.mockMvc.perform(get("/3/settings"))
                .andExpect(status().isOk())
                .andExpect(view().name("settings"));
    }

    @Test
    @WithMockUser(username = "mockuser@testing.com")
    void userCannotAccessOtherUsersDashboard() throws Exception {
        this.mockMvc.perform(get("/2/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("/error"));
    }

    @Test
    @WithMockUser(username = "mockuser@testing.com")
    void userCannotAccessOtherUsersSettings() throws Exception {
        this.mockMvc.perform(get("/2/settings"))
                .andExpect(status().isOk())
                .andExpect(view().name("/error"));
    }

    @Test
    void userCannotAccessDashboardWithoutLoggingIn() throws Exception {
        this.mockMvc.perform(get("/3/dashboard"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/error"));
    }

    @Test
    void userCannotAccessSettingsWithoutLoggingIn() throws Exception {
        this.mockMvc.perform(get("/3/settings"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/error"));
    }

    @Test
    @WithMockUser(username = "mockuser@testing.com")
    void userIsRedirectedBasedOnEndpointID() throws Exception {
        this.mockMvc.perform(get("/3"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/3/dashboard"));

        this.mockMvc.perform(get("/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("/error"));
    }

    @Test
    @WithMockUser(username = "mockuser@testing.com")
    void userIsRedirectedBasedOnEndpointIDWithSlash() throws Exception {
        this.mockMvc.perform(get("/3/"))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/3/dashboard"));

        this.mockMvc.perform(get("/2/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/error"));
    }
}