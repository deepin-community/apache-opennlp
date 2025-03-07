/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package opennlp.tools.cmdline.namefind.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generates randomly created sentences for the English language
 * to compile a text corpus for training purposes.
 * <p>
 * Covers famous books and movies, recent sport events, music highlights, and events
 * from politics and science, distributed across the last 100 years.
 * <p>
 * Dates are formatted as {@code <START:date> ... <END>} in different English date format variations.
 *
 * @implNote Event template phrases generated by AI (MS Copilot).
 *
 * @see opennlp.tools.namefind.NameFinderMEWithDatesTest
 */
public class RandomEnglishNewsGenerator extends AbstractNewsGenerator {

  private static final Logger logger = LoggerFactory.getLogger(RandomEnglishNewsGenerator.class);

  private static final String START_DATE = "<START:date> ";
  private static final String END = " <END>";

  // Origin: https://en.wikipedia.org/wiki/List_of_date_formats_by_country
  private static final String[] DATE_FORMATS = {
      "d MMMM yyyy", // UK
      "dd/mm/yyyy", // UK
      "m/d/yy", // US
      "m/d/yyyy", // US
      "yyyy-mm-dd", // US
      "MMMM d, yyyy", // Australia
      "EEEE, dd/mm/yyyy", // UK with name of day
      "EEEE, yyyy-mm-dd", // US with name of day
      "MMMM yyyy", // month name + year
  };

  private static final String[] SPORTS_EVENTS = {
      "the legendary boxing match between Joe Louis and Max Schmeling took place.",
      "the Olympic Games were held in Rome.",
      "the ‘Miracle of Bern’ occurred, when the German national football team won the World Cup.",
      "the Winter Olympics were held in Lake Placid.",
      "the Football World Cup took place in Italy.",
      "the Olympic Games were held in Atlanta.",
      "the FIFA World Cup was held in Brazil",
      "the Olympic Games took place in Tokyo",
      "the European Football Championship was held in several European cities.",
      "the Winter Olympics were held in Beijing.",
      "the FIFA World Cup was held in Qatar.",
      "the Olympic Games will take place in Paris.",
      "the Brazilian national football team won the World Cup.",
      "Usain Bolt set a new world record in the 100-meter sprint.",
      "Serena Williams won her 23rd Grand Slam title.",
      "the NBA championship final took place, with the Los Angeles Lakers taking the title.",
      "Michael Phelps won his 23rd Olympic gold medal.",
      "the Wimbledon final took place, with Roger Federer winning his eighth title.",
      "the German national football team won the European Championship.",
      "the Super Bowl final took place, with the New England Patriots emerging victorious.",
      "Rafael Nadal won his 13th French Open title.",
      "the UEFA Champions League final took place, with Real Madrid lifting the title."
  };

  private static final String[] MUSIC_EVENTS = {
      "Elvis Presley's first single ‘That's All Right’ was released.",
      "the album ‘A Night at the Opera’ by Queen was released.",
      "the Woodstock Festival began.",
      "the album ‘Nevermind’ by Nirvana was released.",
      "the album ‘25’ by Adele was released.",
      "Michael Jackson, the ‘King of Pop’, died.",
      "the album ‘A Seat at the Table’ by Solange was released.",
      "the album ‘Montero’ by Lil Nas X was released.",
      "the album ‘Motomami’ by Rosalía was released.",
      "the album ‘Midnights’ by Taylor Swift was released.",
      "the album ‘Thriller’ by Michael Jackson was released.",
      "Pink Floyd's ‘The Dark Side of the Moon’ was released.",
      "The Beatles‘ ’Abbey Road‘ was released.",
      "AC/DC's ’Back in Black‘ was released.",
      "Fleetwood Mac's ’Rumours‘ was released.",
      "The Eagles’ ‘Hotel California’ was released.",
      "Bruce Springsteen's album ‘Born to Run’ was released.",
      "Prince's album ‘Purple Rain’.",
      "Pink Floyd's album ‘The Wall’.",
      "Led Zeppelin's album ‘Led Zeppelin IV’."
  };

  private static final String[] CINEMA_EVENTS = {
      "the film ‘Gone with the Wind’ was released in cinemas.",
      "the film ‘Lawrence of Arabia’ was released.",
      "the film ‘Star Wars: A New Hope’ was released in cinemas.",
      "the film ‘Titanic’ was released.",
      "the film ‘Avatar’ was released in cinemas.",
      "the film ‘Frozen’ was released.",
      "the film ‘Thor: Ragnarok’ was released in cinemas.",
      "the film ‘The Godfather’ was released.",
      "the film ‘Schindler's List’ was released.",
      "the film ‘The Lord of the Rings: The Fellowship of the Ring’ was released in cinemas.",
      "the film ‘Forrest Gump’ was released.",
      "the film ‘The Lion King’ was released.",
      "the film ‘Jurassic Park’ was released in cinemas.",
      "the film ‘Inception’ was released.",
      "the film ‘The Matrix’ was released.",
      "the film ‘Gladiator’ was released in cinemas.",
      "the film ‘Pulp Fiction’ was released."
  };

  private static final String[] ECONOMY_EVENTS = {
      "it was announced that the global economy had grown by 3.5%.",
      "an important agreement was signed to promote international cooperation in the field of economics.",
      "a major conference on global economic services took place, at which leading experts discussed " +
              "strategies for improving economic services.",
      "the introduction of the euro as a common currency in Europe was decided.",
      "the World Bank was established.",
      "the European Central Bank was established.",
      "the Bretton Woods Agreement was signed.",
      "the first meeting of the World Economic Forum was held in Davos.",
      "the World Trade Organization (WTO) was established.",
      "the Maastricht Treaty was signed, leading to the establishment of the European Union.",
      "the introduction of the Marshall Plan for economic reconstruction aid after the World War II.",
      "the decision was taken to establish the Organization for Economic Cooperation and Development (OECD).",
      "the first G20 summit took place."
  };

  private static final String[] POLITICS_EVENTS = {
      "parliamentary elections took place in Germany.",
      "a new law was passed to promote digital education.",
      "an important agreement was signed to promote international cooperation in the field of politics.",
      "the Universal Declaration of Human Rights was adopted by the United Nations.",
      "the founding of the United Nations took place.",
      "the Treaty of Versailles was signed, ending the First World War.",
      "NATO was founded.",
      "the first session of the European Parliament took place",
      "the Lisbon Treaty was signed, reforming the European Union",
      "the Berlin Wall was built",
      "the reunification of Germany took place",
      "the Treaty of Rome was signed, leading to the establishment of the" +
              " European Economic Community",
      "the United Nations Charter was signed."
  };

  private static final String[] SCIENCE_EVENTS = {
      "a significant breakthrough in quantum computing research was made",
      "a major scientific project to study climate change was launched",
      "a major scientific symposium was held on the latest developments" +
              " in genetic research",
      "the structure of DNA was discovered by James Watson and Francis Crick",
      "the Apollo 11 mission successfully landed on the moon",
      "the first artificial heart was successfully transplanted",
      "the Human Genome Project was completed",
      "the first cloned sheep, Dolly, was born",
      "the Large Hadron Collider was launched",
      "the first vaccine against COVID-19 was approved",
      "the first image of a black hole was published",
      "the first successful gene therapy was performed in a human",
      "the first space probe landed on Mars",
      "the existence of gravitational waves was detected",
      "the first artificial organ was successfully transplanted",
      "the first CRISPR gene editing was performed in a human",
      "the first all-electric aircraft was tested",
      "the first quantum communication via satellite was carried out",
      "the first successful stem cell therapy was performed in a human",
      "the first image of an exoplanet was published."
  };

  private static final String[] SENTENCE_STARTS_WITH_DATE = {
      "On",
      "In",
      "On the",
      "During the",
      "At the start of",
      "Towards the end of",
      "In the middle of",
      "Shortly after",
      "Around",
      "During",
      "After",
      "Before",
      "Around"
  };

  private static final String[] SENTENCE_STARTS_WITHOUT_DATE = {
      "As reported,",
      "According to an announcement,",
      "According to the latest reports,",
      "According to a statement,",
      "As reported by Reuters,",
      "According to the authorities,",
      "According to experts,"
  };

  private static final int NUM_SENTENCES = 10000;

  @Override
  String[] getSupportedDateFormats() {
    return DATE_FORMATS;
  }

  public static void main(String[] args) {
    String outputFileName;
    if (args.length != 1) {
      outputFileName = "RandomNewsWithGeneratedDates_EN.train";
    } else {
      outputFileName = args[0];
    }

    RandomEnglishNewsGenerator newsGen = new RandomEnglishNewsGenerator();
    try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(outputFileName))) {
      for (int i = 0; i < NUM_SENTENCES; i++) {
        writer.write(newsGen.generateSentence());
        writer.newLine();
      }
      logger.info("Text corpus with '{}' sentences generated and written to: '{}'",
              NUM_SENTENCES, outputFileName);
    } catch (IOException e) {
      logger.error(e.getLocalizedMessage(), e);
    }
  }

  private String generateSentence() {
    String[] eventTypes = {"sports", "music", "cinema", "economy", "politics", "science"};
    String eventType = eventTypes[new Random().nextInt(eventTypes.length)];

    String event = switch (eventType) {
      case "sports" -> SPORTS_EVENTS[new Random().nextInt(SPORTS_EVENTS.length)];
      case "music" -> MUSIC_EVENTS[new Random().nextInt(MUSIC_EVENTS.length)];
      case "cinema" -> CINEMA_EVENTS[new Random().nextInt(CINEMA_EVENTS.length)];
      case "economy" -> ECONOMY_EVENTS[new Random().nextInt(ECONOMY_EVENTS.length)];
      case "politics" -> POLITICS_EVENTS[new Random().nextInt(POLITICS_EVENTS.length)];
      case "science" -> SCIENCE_EVENTS[new Random().nextInt(SCIENCE_EVENTS.length)];
      default -> "";
    };

    Random random = new Random();
    String sentence;
    if (random.nextDouble() < 0.15) {
      String date = START_DATE + formatDateWithTags(generateRandomDate(cal), Locale.ENGLISH) + END;
      String sentenceStart = SENTENCE_STARTS_WITH_DATE[
              new Random().nextInt(SENTENCE_STARTS_WITH_DATE.length)];
      sentence = String.format("%s %s %s", sentenceStart, date, event);
    } else {
      String sentenceStart = SENTENCE_STARTS_WITHOUT_DATE[
              new Random().nextInt(SENTENCE_STARTS_WITHOUT_DATE.length)];
      sentence = String.format("%s %s", sentenceStart, event);
    }
    return sentence;
  }

}
