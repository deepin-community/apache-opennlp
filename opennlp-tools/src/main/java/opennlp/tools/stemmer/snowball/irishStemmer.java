// CHECKSTYLE:OFF
/*

Copyright (c) 2001, Dr Martin Porter
Copyright (c) 2002, Richard Boulton
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
    * this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
    * notice, this list of conditions and the following disclaimer in the
    * documentation and/or other materials provided with the distribution.
    * Neither the name of the copyright holders nor the names of its contributors
    * may be used to endorse or promote products derived from this software
    * without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */

// Generated by Snowball (build from 867c4ec70debd4daa7fb4d5a9f7759b47887d0b9)
package opennlp.tools.stemmer.snowball;


/**
 * This class implements the stemming algorithm defined by a snowball script.
 * <p>
 * Generated by Snowball (build from 867c4ec70debd4daa7fb4d5a9f7759b47887d0b9) - <a href="https://github.com/snowballstem/snowball">https://github.com/snowballstem/snowball</a>
 * </p>
 */
@SuppressWarnings("unused")
public class irishStemmer extends AbstractSnowballStemmer {


  private final static Among a_0[] = {
      new Among("b'", -1, 1),
      new Among("bh", -1, 4),
      new Among("bhf", 1, 2),
      new Among("bp", -1, 8),
      new Among("ch", -1, 5),
      new Among("d'", -1, 1),
      new Among("d'fh", 5, 2),
      new Among("dh", -1, 6),
      new Among("dt", -1, 9),
      new Among("fh", -1, 2),
      new Among("gc", -1, 5),
      new Among("gh", -1, 7),
      new Among("h-", -1, 1),
      new Among("m'", -1, 1),
      new Among("mb", -1, 4),
      new Among("mh", -1, 10),
      new Among("n-", -1, 1),
      new Among("nd", -1, 6),
      new Among("ng", -1, 7),
      new Among("ph", -1, 8),
      new Among("sh", -1, 3),
      new Among("t-", -1, 1),
      new Among("th", -1, 9),
      new Among("ts", -1, 3)
  };

  private final static Among a_1[] = {
      new Among("\u00EDochta", -1, 1),
      new Among("a\u00EDochta", 0, 1),
      new Among("ire", -1, 2),
      new Among("aire", 2, 2),
      new Among("abh", -1, 1),
      new Among("eabh", 4, 1),
      new Among("ibh", -1, 1),
      new Among("aibh", 6, 1),
      new Among("amh", -1, 1),
      new Among("eamh", 8, 1),
      new Among("imh", -1, 1),
      new Among("aimh", 10, 1),
      new Among("\u00EDocht", -1, 1),
      new Among("a\u00EDocht", 12, 1),
      new Among("ir\u00ED", -1, 2),
      new Among("air\u00ED", 14, 2)
  };

  private final static Among a_2[] = {
      new Among("\u00F3ideacha", -1, 6),
      new Among("patacha", -1, 5),
      new Among("achta", -1, 1),
      new Among("arcachta", 2, 2),
      new Among("eachta", 2, 1),
      new Among("grafa\u00EDochta", -1, 4),
      new Among("paite", -1, 5),
      new Among("ach", -1, 1),
      new Among("each", 7, 1),
      new Among("\u00F3ideach", 8, 6),
      new Among("gineach", 8, 3),
      new Among("patach", 7, 5),
      new Among("grafa\u00EDoch", -1, 4),
      new Among("pataigh", -1, 5),
      new Among("\u00F3idigh", -1, 6),
      new Among("acht\u00FAil", -1, 1),
      new Among("eacht\u00FAil", 15, 1),
      new Among("gineas", -1, 3),
      new Among("ginis", -1, 3),
      new Among("acht", -1, 1),
      new Among("arcacht", 19, 2),
      new Among("eacht", 19, 1),
      new Among("grafa\u00EDocht", -1, 4),
      new Among("arcachta\u00ED", -1, 2),
      new Among("grafa\u00EDochta\u00ED", -1, 4)
  };

  private final static Among a_3[] = {
      new Among("imid", -1, 1),
      new Among("aimid", 0, 1),
      new Among("\u00EDmid", -1, 1),
      new Among("a\u00EDmid", 2, 1),
      new Among("adh", -1, 2),
      new Among("eadh", 4, 2),
      new Among("faidh", -1, 1),
      new Among("fidh", -1, 1),
      new Among("\u00E1il", -1, 2),
      new Among("ain", -1, 2),
      new Among("tear", -1, 2),
      new Among("tar", -1, 2)
  };

  private static final char g_v[] = {17, 65, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 17, 4, 2};

  private int I_p2;
  private int I_p1;
  private int I_pV;


  private boolean r_mark_regions() {
    I_pV = limit;
    I_p1 = limit;
    I_p2 = limit;
    int v_1 = cursor;
    lab0:
    {
      golab1:
      while (true) {
        lab2:
        {
          if (!(in_grouping(g_v, 97, 250))) {
            break lab2;
          }
          break golab1;
        }
        if (cursor >= limit) {
          break lab0;
        }
        cursor++;
      }
      I_pV = cursor;
      golab3:
      while (true) {
        lab4:
        {
          if (!(out_grouping(g_v, 97, 250))) {
            break lab4;
          }
          break golab3;
        }
        if (cursor >= limit) {
          break lab0;
        }
        cursor++;
      }
      I_p1 = cursor;
      golab5:
      while (true) {
        lab6:
        {
          if (!(in_grouping(g_v, 97, 250))) {
            break lab6;
          }
          break golab5;
        }
        if (cursor >= limit) {
          break lab0;
        }
        cursor++;
      }
      golab7:
      while (true) {
        lab8:
        {
          if (!(out_grouping(g_v, 97, 250))) {
            break lab8;
          }
          break golab7;
        }
        if (cursor >= limit) {
          break lab0;
        }
        cursor++;
      }
      I_p2 = cursor;
    }
    cursor = v_1;
    return true;
  }

  private boolean r_initial_morph() {
    int among_var;
    bra = cursor;
    among_var = find_among(a_0);
    if (among_var == 0) {
      return false;
    }
    ket = cursor;
    switch (among_var) {
      case 1:
        slice_del();
        break;
      case 2:
        slice_from("f");
        break;
      case 3:
        slice_from("s");
        break;
      case 4:
        slice_from("b");
        break;
      case 5:
        slice_from("c");
        break;
      case 6:
        slice_from("d");
        break;
      case 7:
        slice_from("g");
        break;
      case 8:
        slice_from("p");
        break;
      case 9:
        slice_from("t");
        break;
      case 10:
        slice_from("m");
        break;
    }
    return true;
  }

  private boolean r_RV() {
    return I_pV <= cursor;
  }

  private boolean r_R1() {
    return I_p1 <= cursor;
  }

  private boolean r_R2() {
    return I_p2 <= cursor;
  }

  private boolean r_noun_sfx() {
    int among_var;
    ket = cursor;
    among_var = find_among_b(a_1);
    if (among_var == 0) {
      return false;
    }
    bra = cursor;
    switch (among_var) {
      case 1:
        if (!r_R1()) {
          return false;
        }
        slice_del();
        break;
      case 2:
        if (!r_R2()) {
          return false;
        }
        slice_del();
        break;
    }
    return true;
  }

  private boolean r_deriv() {
    int among_var;
    ket = cursor;
    among_var = find_among_b(a_2);
    if (among_var == 0) {
      return false;
    }
    bra = cursor;
    switch (among_var) {
      case 1:
        if (!r_R2()) {
          return false;
        }
        slice_del();
        break;
      case 2:
        slice_from("arc");
        break;
      case 3:
        slice_from("gin");
        break;
      case 4:
        slice_from("graf");
        break;
      case 5:
        slice_from("paite");
        break;
      case 6:
        slice_from("\u00F3id");
        break;
    }
    return true;
  }

  private boolean r_verb_sfx() {
    int among_var;
    ket = cursor;
    among_var = find_among_b(a_3);
    if (among_var == 0) {
      return false;
    }
    bra = cursor;
    switch (among_var) {
      case 1:
        if (!r_RV()) {
          return false;
        }
        slice_del();
        break;
      case 2:
        if (!r_R1()) {
          return false;
        }
        slice_del();
        break;
    }
    return true;
  }

  public boolean stem() {
    int v_1 = cursor;
    r_initial_morph();
    cursor = v_1;
    r_mark_regions();
    limit_backward = cursor;
    cursor = limit;
    int v_3 = limit - cursor;
    r_noun_sfx();
    cursor = limit - v_3;
    int v_4 = limit - cursor;
    r_deriv();
    cursor = limit - v_4;
    int v_5 = limit - cursor;
    r_verb_sfx();
    cursor = limit - v_5;
    cursor = limit_backward;
    return true;
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof irishStemmer;
  }

  @Override
  public int hashCode() {
    return irishStemmer.class.getName().hashCode();
  }


}

