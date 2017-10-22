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

class EsaClient(private val accessToken: String = "",
                private val apiEndPoint: String = "",
                val currentTeam: String = "") {

  private type PathStr = String

  private type HeaderKey = String

  private type HeaderValue = String

  def sendGet(path: PathStr,
              headers: Map[HeaderKey, HeaderValue] = Map(),
              params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendSkinnyRequest(Method.GET, path, headers, params)

  def sendPost(path: PathStr,
               headers: Map[HeaderKey, HeaderValue] = Map(),
               params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendSkinnyRequest(Method.POST, path, headers, params)

  def sendPut(path: PathStr,
              headers: Map[HeaderKey, HeaderValue] = Map(),
              params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendSkinnyRequest(Method.PUT, path, headers, params)

  // TODO Skinny framework does not have PATCH Method...
  def sendPatch(path: PathStr,
                headers: Map[HeaderKey, HeaderValue] = Map(),
                params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendSkinnyRequest(Method.PUT, path, headers, params)

  def sendDelete(path: PathStr,
                 headers: Map[HeaderKey, HeaderValue] = Map(),
                 params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendSkinnyRequest(Method.DELETE, path, headers, params)

  private def sendSkinnyRequest(method: Method,
                                path: PathStr,
                                headers: Map[HeaderKey, HeaderValue],
                                params: Seq[(String, Any)]): EsaResponse = {
    val req = new Request(createSkinnyUrl(path))
    req.headers ++= headers
    params.foreach(req.queryParam)

    method match {
      case Method.GET    => EsaResponse(HTTP.get(req))
      case Method.POST   => EsaResponse(HTTP.put(req))
      case Method.PUT    => EsaResponse(HTTP.put(req))
      case Method.DELETE => EsaResponse(HTTP.delete(req))
    }
  }

  private def createSkinnyUrl(path: PathStr): String =
    if (!apiEndPoint.isEmpty) apiEndPoint + path else "https://api.esa.io" + path
}
