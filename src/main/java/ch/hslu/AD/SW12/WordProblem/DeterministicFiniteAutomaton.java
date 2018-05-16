package ch.hslu.AD.SW12.WordProblem;

/**
 * Übung: Endliche Automaten und formale Sprachen (A3)
 * Aufgabe: Wortproblem mit Hilfe eine DEA lösen
 *
 * @author Fabian Gröger
 * @version 16.05.2018
 */
public class DeterministicFiniteAutomaton {

    public static boolean isWordLanguage(final String string) {
        State state = State.z0; // initial state

        for (char c : string.toCharArray()) {
            switch (c) {
                case '0':
                    switch (state) {
                        case z0:
                            state = State.z1; // if 0 & is at z0 -> z1
                            break;
                        case z2:
                            state = State.z4; // if 0 & is at z2 -> z4
                            break;
                        default:
                            return false;
                    }
                    break;

                case '1':
                    switch (state) {
                        case z1:
                            state = State.z2; // if 1 & is at z1 -> z2
                            break;
                        case z2:
                            state = State.z3; // if 1 & is at z2 -> z3
                            break;
                        case z3:
                            state = State.z2; // if 1 & is at z3 -> z2
                            break;
                        case z4:
                            state = State.z2; // if 1 & is at z4 -> z2
                            break;
                        default:
                            return false;
                    }
                    break;

                default:
                    return false;
            }
        }

        return State.z4.equals(state) || State.z1.equals(state); // z4 & z1 -> terminal symbols
    }
}
