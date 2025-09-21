/**
 * Author: Madhu
 * User:madhu
 * Date:14/9/25
 * Time:3:25 PM
 * Project: similarity-search-example
 */

package io.madhu.inference.aspect;


import io.madhu.inference.util.UsageContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.ai.chat.metadata.Usage;

@Slf4j
@Aspect
public class HandBookResultEnrichAspect {

    @AfterReturning(pointcut = "execution(* io.madhu.inference.controller.HandBookChatController.chat(..))",
            returning = "response")
    public Object enrichResponse(Object response) {
        log.info("Enriching the response");
        if (response instanceof String res) {
            Usage usage = UsageContext.get();
            log.info("Total Number of tokens is Prompt Tokens : {}  Total Generation Tokens{} ,TOtal Tokens {} ",
                    usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens());
            String enriched = res + String.format(
                    "<br><br> Prompt Tokens: %d , Generation Tokens: %d , Total Tokens: %d",
                    usage.getPromptTokens(), usage.getCompletionTokens(), usage.getTotalTokens()
            );
            UsageContext.clear(); // cleanup
            return enriched;
        }
        return response;
    }
}
