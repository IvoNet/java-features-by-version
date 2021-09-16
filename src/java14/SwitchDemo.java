/*
 * Copyright 2019 Ivo Woltring <WebMaster@ivonet.nl>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java14;

public class SwitchDemo {

    public static void main(final String[] args) {
        final var demo = new SwitchDemo();
        System.out.println("demo.klasiekeSwitch(1) = " + demo.classicSwitchStatement(1));
        System.out.println("demo.klasiekeSwitch(7) = " + demo.classicSwitchStatement(7));
        System.out.println("demo.klasiekeSwitch(0) = " + demo.classicSwitchStatement(0));

        System.out.println("demo.dag(1) = " + demo.day(1));
        System.out.println("demo.dag(7) = " + demo.day(7));
        System.out.println("demo.dag(0) = " + demo.day(0));

        System.out.println("demo.isWeekend(\"Wednesday\") = " + demo.isWeekend("Wednesday"));
        System.out.println("demo.isWeekend(\"Saturday\") = " + demo.isWeekend("Saturday"));
        System.out.println("demo.isWeekend(\"Statterblab\") = " + demo.isWeekend("Statterblab"));
        System.out.println("demo.isWeekend(\"Nope\") = " + demo.isWeekend("Nope"));


    }

    public String classicSwitchStatement(final int dag) {
        final String waarde;
        switch (dag) {
            case 1:     // no break used to fall through to the next case. Fragile code
            case 2:
            case 3:
            case 4:
            case 5:
                waarde = "weekday";
                break;
            case 6:
            case 7:
                waarde = "weekend";
                break;
            default:
                waarde = "unknown";
        }
        return waarde;
    }

    public String day(final int day) {
        return switch (day) { //switch as an expression
            case 1, 2, 3, 4, 5 -> "weekday";
            case 6, 7 -> "weekend";
            default -> "unknown";
        };
    }

    public boolean isWeekend(final String day) {
        return switch (day) {
            case "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" -> false;
            case "Saturday", "Sunday" -> true;
            default -> {
                if (day.startsWith("S")) {
                    System.out.println("Looks like weekend");
                    yield true; //yield in complex situations
                }

                System.out.printf("Unknown day: %s%n", day);
                yield false;
            }
        };
    }
}
