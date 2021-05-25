/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.parser.util;

import io.confluent.ksql.util.ParserUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ParserUtilTest {
  @Test
  public void shouldEscapeStringIfLiteral() {
    assertThat(ParserUtil.escapeIfLiteral("END"), equalTo("`END`"));
  }

  @Test
  public void shouldNotEscapeStringIfNotLiteral() {
    assertThat(ParserUtil.escapeIfLiteral("NOT_A_LITERAL"), equalTo("NOT_A_LITERAL"));
  }

  @Test
  public void shouldHaveReservedLiteralInReservedSet() {
    assertThat(ParserUtil.isReservedIdentifier("FROM"), is(true));
  }

  @Test
  public void shouldExcludeNonReservedLiteralsFromReservedSet() {
    // i.e. those in the "nonReserved" rule in SqlBase.g4
    assertThat(ParserUtil.isReservedIdentifier("SHOW"), is(false));
  }
}