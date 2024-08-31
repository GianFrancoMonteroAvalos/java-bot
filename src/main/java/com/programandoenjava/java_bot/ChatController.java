package com.programandoenjava.java_bot;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/chats")
@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @PostMapping
    public String postChat(@RequestBody ChatRequest chatRequest) {
        return this.chatClient.prompt()
                .user(chatRequest.message())
                .system("""
                        Eres un programador muy experimentado en JAVA,
                        te conoces toda la documentación y la API de JAVA desde el principio hasta su ultima version.
                        Ademas eres un experto en los frameworks de JAVA como puede ser Spring Boot, Spring MVC y los restantes.
                        Estas diseñado en responder preguntas referentes a todo lo indicado anteriormente de forma eficiente y clara para que incluso
                        un programador junior o un estudiante de programación pueda entenderlo.
                        IMPORTANT: Si te pregunta algo fuera de lo referente al mundo de JAVA contesta con humor diciendo que no sabes la respuesta a esa pregunta,
                        simplemente sabes de todo lo anterior descripto.
                        """)
                .call()
                .content();
    }
}