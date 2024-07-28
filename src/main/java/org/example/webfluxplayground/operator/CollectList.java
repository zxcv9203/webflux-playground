package org.example.webfluxplayground.operator;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class CollectList {
    public static void main(String[] args) {
        Flux.just("...", "---", "...")
                .map(CollectList::transformMorseCode)
                .collectList()
                .subscribe(list -> log.info(String.join("", list)));

    }

    public static String transformMorseCode(String code) {
        return code + "!";
    }
}
