/**
 * Author: Madhu
 * User:madhu
 * Date:14/9/25
 * Time:3:19 PM
 * Project: similarity-search-example
 */

package io.madhu.inference.util;

import org.springframework.ai.chat.metadata.Usage;

public class UsageContext {

    private static final ThreadLocal<Usage> USAGE_HOLDER = new ThreadLocal<>();

    public static void set(Usage usage) {
        USAGE_HOLDER.set(usage);
    }

    public static Usage get() {
        return USAGE_HOLDER.get();
    }

    public static void clear() {
        USAGE_HOLDER.remove();
    }
}
