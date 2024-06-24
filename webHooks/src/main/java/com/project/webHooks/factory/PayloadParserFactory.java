package com.project.webHooks.factory;

import com.project.webHooks.parser.ProviderPayloadParser;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PayloadParserFactory {

    private final Map<String, ProviderPayloadParser> parserMap;

    public PayloadParserFactory(List<ProviderPayloadParser> parsers) {
        parserMap = parsers.stream().collect(Collectors.toMap(
                parser -> parser.getClass().getSimpleName().replace("PayloadParser", "").toLowerCase(),
                parser -> parser
        ));
    }

    public ProviderPayloadParser getParser(String provider) {
        ProviderPayloadParser parser = parserMap.get(provider.toLowerCase());
        if (parser == null) {
            throw new IllegalArgumentException("Unknown provider: " + provider);
        }
        return parser;
    }
}
