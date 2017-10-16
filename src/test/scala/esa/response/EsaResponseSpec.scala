package esa.response

import esa.response.EsaResponse
import org.scalatest.{Matchers, WordSpec}
import skinny.http.Response

/*
 * Copyright 2017 Yuki Toyoda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

class EsaResponseSpec extends WordSpec with Matchers {

  val res = EsaResponse(
    Response(200,
             Map("keyA" -> "valueA"),
             Map(), // headerField is not in use
             Map(), // rawCookies are not in use
             Some("utf-8"), // charset is not in use
            "body".getBytes))

  "EsaResponse" should {
    "be available" in {
      res.status should equal(200)
      res.header("keyA") should equal(Some("valueA"))
      res.headers should equal(Map("keyA" -> "valueA"))
      res.body should equal("body")
    }
  }
}
