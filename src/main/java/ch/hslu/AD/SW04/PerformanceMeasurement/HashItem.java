package ch.hslu.AD.SW04.PerformanceMeasurement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Einfache Performance-Messung und Analyse
 *
 * @author Fabian Gröger
 * @version 19.03.2018
 */
public final class HashItem {

    private static final Logger logger = LogManager.getLogger("CharWrapper");

    private final Character character;

    public HashItem(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

    @Override
    public boolean equals(Object other) {
        boolean equality = false;
        if (other == null) {
            equality = false;
        } else if (this == other) {
            equality = true;
        } else if (!(other instanceof HashItem)) {
            return false;
        } else {
            HashItem otherCharWrapper = (HashItem) other;
            equality = character.equals(otherCharWrapper.character);
        }
        logger.debug(String.format("'%s'.equals('%s')? %s", this.toString(), other.toString(), equality));
        return equality;
    }

    @Override
    public int hashCode() {
        int hashCode = character.hashCode();
        logger.debug(String.format("'%s'.hashCode() == %d", this.toString(), hashCode));
        return hashCode;
    }

    @Override
    public String toString() {
        return String.valueOf(character);
    }
}
