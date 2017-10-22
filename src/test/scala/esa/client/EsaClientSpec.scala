package esa.client

import org.scalatest.{Matchers, WordSpec}

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

class EsaClientSpec extends WordSpec with Matchers {

  private[this] val esaClient = new EsaClient()

  "EsaClient" should {
    "sendGet" in {
      val response = esaClient.sendGet("/v1/teams", Map())
      response.status should equal(401)
      response.body should equal(
        "{\"error\":\"unauthorized\",\"message\":\"Unauthorized\"}")
    }

    "sendPost" in {
      val response = esaClient.sendPost("/v1/teams", Map())
      response.status should equal(404)
      response.headers should not equal empty
    }

    "sendPut" in {
      val response = esaClient.sendPut("/v1/teams", Map())
      response.status should equal(404)
      response.headers should not equal empty
    }

    "sendPatch" in {
      val response = esaClient.sendPatch("/v1/teams", Map())
      response.status should equal(404)
      response.headers should not equal empty
    }

    "sendDelete" in {
      val response = esaClient.sendDelete("/v1/teams", Map())
      response.status should equal(404)
      response.headers should not equal empty
    }
  }
}
