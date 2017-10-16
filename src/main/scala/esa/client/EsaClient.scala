package esa.client

import esa.response.EsaResponse
import skinny.http.{HTTP, Method, Request}

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

object EsaClient {

  /**
    * Create <code>EsaClient</code> instance.
    * @param accessToken Access token
    * @param apiEndPoint End point of your api
    * @param currentTeam Current team name
    * @return EsaClient instance
    */
  def newInstance(accessToken: String = "",
                  apiEndPoint: String = "",
                  currentTeam: String = ""): EsaClient =
    new EsaClient(accessToken, apiEndPoint, currentTeam)
}

class EsaClient(_accessToken: String = "",
                _apiEndPoint: String = "",
                _currentTeam: String = "") {

  type Url = String

  type HeaderKey = String

  type HeaderValue = String

  def sendGet(url: Url,
              headers: Map[HeaderKey, HeaderValue],
              params: (String, Any)*): EsaResponse =
    sendSkinnyRequest(Method.GET, url, headers, params)

  private def sendSkinnyRequest(method: Method,
                                url: Url,
                                headers: Map[HeaderKey, HeaderValue],
                                params: Seq[(String, Any)]): EsaResponse = {
    val req = new Request(url)
    req.headers ++= headers
    params.foreach(req.queryParam)

    method match {
      case Method.GET    => EsaResponse.of(HTTP.get(req))
      case Method.POST   => EsaResponse.of(HTTP.put(req))
      case Method.PUT    => EsaResponse.of(HTTP.put(req))
      case Method.DELETE => EsaResponse.of(HTTP.delete(req))
    }
  }
}
