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

package opennlp.tools.formats.conllu;

import opennlp.tools.util.InvalidFormatException;

public class ConlluWordLine {

  private final String id;
  private final String form;
  private final String lemma;
  private final String uPosTag;
  private final String xPosTag;
  private final String feats;
  private final String head;
  private final String deprel;
  private final String deps;
  private final String misc;

  ConlluWordLine(String id, String form, String lemma, String uPosTag, String xPosTag,
                 String feats, String head, String deprel, String deps, String misc) {
    this.id = id;
    this.form = form;
    this.lemma = lemma;
    this.uPosTag = uPosTag;
    this.xPosTag = xPosTag;
    this.feats = feats;
    this.head = head;
    this.deprel = deprel;
    this.deps = deps;
    this.misc = misc;
  }

  ConlluWordLine(String line) throws InvalidFormatException {

    String[] fields = line.split("\t");

    if (fields.length != 10) {
      throw new InvalidFormatException("Line [" + line + "] must have exactly 10 fields");
    }

    id = fields[0];
    form = fields[1];
    lemma = fields[2];
    uPosTag = fields[3];
    xPosTag = fields[4];
    feats = fields[5];
    head = fields[6];
    deprel = fields[7];
    deps = fields[8];
    misc = fields[9];
  }

  /**
   * @return Retrieves the word index. An Integer starting at {@code 1} for each new sentence;
   * may be a range for multiword tokens; may be a decimal number for empty nodes.
   */
  public String getId() {
    return id;
  }

  /**
   * @return Retrieves the word form or punctuation symbol.
   */
  public String getForm() {
    return form;
  }

  /**
   * @return Retrieves the lemma or stem of the word form.
   */
  public String getLemma() {
    return lemma;
  }

  /**
   * @param tagset The {@link ConlluTagset type of tag} to retrieve, either universal
   *               ({@link ConlluTagset#U}) or language specific ({@link ConlluTagset#X}).
   *
   * @return Retrieves the Universal part-of-speech tag or the language-specific part-of-speech tag;
   * underscore if not available.
   * @throws IllegalStateException Thrown if a non-supported {@link ConlluTagset} was specified.
   */
  public String getPosTag(ConlluTagset tagset) {
    return switch (tagset) {
      case U -> uPosTag;
      case X -> xPosTag;
      default -> throw new IllegalStateException("Unexpected tagset value: " + tagset);
    };
  }

  /**
   * @return Retrieves morphological features from the universal feature inventory
   * or from a defined language-specific extension; underscore if not available.
   */
  public String getFeats() {
    return feats;
  }

  /**
   * @return Retrieves the head of the current word, which is either a value of ID or zero (0).
   */
  public String getHead() {
    return head;
  }

  /**
   * @return Retrieves the Universal dependency relation to the HEAD (root if HEAD = 0)
   * or a defined language-specific subtype of one.
   */
  public String getDeprel() {
    return deprel;
  }

  /**
   * @return Retrieves the enhanced dependency graph in the form of a list of
   * head-deprel pairs.
   */
  public String getDeps() {
    return deps;
  }

  /**
   * @return Retrieves any other annotation.
   */
  public String getMisc() {
    return misc;
  }
}
