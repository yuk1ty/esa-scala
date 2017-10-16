package esa.client

import esa.response.EsaResponse

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

case class EsaApiMethods(private val accessToken: String = "",
                         private val apiEndPoint: String = "",
                         private val currentTeam: String = "")
    extends EsaClient(accessToken, apiEndPoint, currentTeam) {

  def user(headers: Map[HeaderKey, HeaderValue] = Map(),
           params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendGet("/v1/user", headers, params)

  def teams(headers: Map[HeaderKey, HeaderValue] = Map(),
            params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendGet("/v1/teams", headers, params)

  def team(teamName: String,
           headers: Map[HeaderKey, HeaderValue] = Map(),
           params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendGet(s"/v1/teams/$currentTeam", headers, params)

  def stats(headers: Map[HeaderKey, HeaderValue] = Map(),
            params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendGet(s"/v1/teams/$currentTeam/stats", headers, params)

  def members(headers: Map[HeaderKey, HeaderValue] = Map(),
              params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendGet(s"/v1/teams/$currentTeam/members", headers, params)

  def posts(headers: Map[HeaderKey, HeaderValue] = Map(),
            params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendGet(s"/v1/teams/$currentTeam/posts", headers, params)

  def post(postNumber: Int,
           headers: Map[HeaderKey, HeaderValue] = Map(),
           params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendPost(s"/v1/teams/$currentTeam/posts/$postNumber", headers, params)

  def createPost(headers: Map[HeaderKey, HeaderValue] = Map(),
                 params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendPost(s"/v1/teams/$currentTeam/posts", headers, params)

  // TODO updatePost

  def deletePost(postNumber: Int,
                 headers: Map[HeaderKey, HeaderValue] = Map(),
                 params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendDelete(s"/v1/teams/$currentTeam/posts/$postNumber")

  def comments(postNumber: Option[Int],
               headers: Map[HeaderKey, HeaderValue] = Map(),
               params: Seq[(String, Any)] = Seq()): EsaResponse =
    postNumber match {
      case Some(pn) =>
        sendGet(s"/v1/teams/$currentTeam/posts/$pn/comments", headers, params)
      case None => sendGet(s"/v1/teams/$currentTeam/comments", headers, params)
    }

  def createComment(postNumber: Int,
                    headers: Map[HeaderKey, HeaderValue] = Map(),
                    params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendPost(s"/v1/teams/$currentTeam/posts/$postNumber/comments",
             headers,
             params)

  // TODO updateComment

  def deleteComment(commentId: Long,
                    headers: Map[HeaderKey, HeaderValue] = Map(),
                    params: Seq[(String, Any)] = Seq()): EsaResponse =
    sendDelete(s"/v1/teams/$currentTeam/comments/$commentId", headers, params)
}
