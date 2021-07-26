package nex.zktn.your_offer.util;

import java.util.UUID;

public class GeneratorID {

    private GeneratorID() {

    }

    public static String generateUID() {
        return UUID.randomUUID().toString();
    }


}
