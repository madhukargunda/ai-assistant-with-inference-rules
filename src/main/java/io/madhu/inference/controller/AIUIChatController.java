/**
 * Author: Madhu
 * User:madhu
 * Date:7/9/25
 * Time:12:32 PM
 * Project: similarity-search-example
 */

package io.madhu.inference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AIUIChatController {

    @GetMapping("/chat")
    public String chatPage() {
        return "chat"; // Thymeleaf template
    }

    @GetMapping("/chatevent")
    public String chatEventsPage() {
        return "chatevent"; // Thymeleaf template
    }
}
