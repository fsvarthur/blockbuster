package com.example.blockbuster.Controller;


import com.example.blockbuster.Service.CategoriaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoriaController.class)
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    @Test
    public void getCategoria_shouldReturnJsonArray() throws Exception{
        /*
        * Message firstMessage = new Message("First Message");
            List<Message> allMessages = Arrays.asList(firstMessage);
            when(service.getMessages()).thenReturn(allMessages);
            mvc.perform(get("/api/messages").contentType(MediaType.
            APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].text", is(firstMessage.getText())));
        * */
    }
}
