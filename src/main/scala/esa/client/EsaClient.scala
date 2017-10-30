package esa.client

import dispatch.{Http, Req}
import esa.http._
import esa.response.EsaResponse
import org.asynchttpclient.Response

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.language.postfixOps

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

class EsaClient(private val accessToken: String = "",
                private val apiEndPoint: String = "",
                val currentTeam: String = "") {

  import scala.concurrent.ExecutionContext.Implicits.global

  private lazy val accessTokenHeader = Map(
    "Authorization" -> s"Bearer $accessToken")

  private type PathStr = String

  def sendGet(path: PathStr,
              params: Map[String, String] = Map(),
              headers: Map[String, String] = Map()): EsaResponse =
    sendRequest(GET, path, headers, params)

  def sendPut(path: PathStr,
              params: Map[String, String] = Map(),
              headers: Map[String, String] = Map(),
              requestBody: String): EsaResponse =
    sendRequest(PUT, path, headers, params, requestBody)

  def sendPost(path: PathStr,
               params: Map[String, String] = Map(),
               headers: Map[String, String] = Map(),
               requestBody: String): EsaResponse =
    sendRequest(POST, path, headers, params, requestBody)

  def sendPatch(path: PathStr,
                params: Map[String, String] = Map(),
                headers: Map[String, String] = Map(),
                requestBody: String): EsaResponse =
    sendRequest(PATCH, path, headers, params, requestBody)

  def sendDelete(path: PathStr,
                 params: Map[String, String] = Map(),
                 headers: Map[String, String] = Map()): EsaResponse =
    sendRequest(DELETE, path, headers, params)

  private def sendRequest(method: EsaMethod,
                          path: PathStr,
                          headers: Map[String, String],
                          params: Map[String, String],
                          requestBody: String = ""): EsaResponse = {
    // 外部で構築済みの文字列を URL として使用する必要があるので、ここではフルパスの文字列を受け取るようにしています。
    val req = Req(_.setUrl(constructUri(path))) <:< accessTokenHeader <:< headers <<? params

    method match {
      case GET    => EsaResponse(send(req GET))
      case PUT    => EsaResponse(send(req << requestBody PUT))
      case POST   => EsaResponse(send(req << requestBody POST))
      case PATCH  => EsaResponse(send(req << requestBody PATCH))
      case DELETE => EsaResponse(send(req DELETE))
    }
  }

  private def send(req: Req): Response =
    Await.result(
      Http.default(req <:< Map("Content-Type" -> EsaClient.CONTENT_TYPE)),
      Duration.Inf)

  private def constructUri(path: String): String =
    if (apiEndPoint.isEmpty) "https://api.esa.io" + path
    else apiEndPoint + path
}

object EsaClient {

  private val CONTENT_TYPE: String = "application/json"
}
