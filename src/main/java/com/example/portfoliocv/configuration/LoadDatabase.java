package com.example.portfoliocv.configuration;

import com.example.portfoliocv.entity.WoWCharacter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoadDatabase {
        public static int WowCharacterCount=2;
        private static List<WoWCharacter> wowCharacters =new ArrayList<>();
        static
        {
//adding users to the list
            wowCharacters.add(new WoWCharacter("Obstig", 60, "alliance", "human", "mage"));
            wowCharacters.add(new WoWCharacter("Obstsalat", 51, "alliance", "draenei", "death-knight"));
        }
}
