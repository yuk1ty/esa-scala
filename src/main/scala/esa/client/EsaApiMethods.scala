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

case class EsaApiMethods(private val esaClient: EsaClient) {

  private type PathStr = String

  private type HeaderKey = String

  private type HeaderValue = String

  private val currentTeam: String = esaClient.currentTeam

  def user(headers: Map[HeaderKey, HeaderValue] = Map(),
           params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendGet("/v1/user", headers, params)

  def teams(headers: Map[HeaderKey, HeaderValue] = Map(),
            params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendGet("/v1/teams", headers, params)

  def team(teamName: String,
           headers: Map[HeaderKey, HeaderValue] = Map(),
           params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam", headers, params)

  def stats(headers: Map[HeaderKey, HeaderValue] = Map(),
            params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam/stats", headers, params)

  def members(headers: Map[HeaderKey, HeaderValue] = Map(),
              params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam/members", headers, params)

  def posts(headers: Map[HeaderKey, HeaderValue] = Map(),
            params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam/posts", headers, params)

  def post(postNumber: Long,
           headers: Map[HeaderKey, HeaderValue] = Map(),
           params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendPost(s"/v1/teams/$currentTeam/posts/$postNumber",
                       headers,
                       params)

  def createPost(headers: Map[HeaderKey, HeaderValue] = Map(),
                 params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendPost(s"/v1/teams/$currentTeam/posts", headers, params)

  // TODO updatePost

  def deletePost(postNumber: Long,
                 headers: Map[HeaderKey, HeaderValue] = Map(),
                 params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendDelete(s"/v1/teams/$currentTeam/posts/$postNumber")

  def comments(postNumber: Option[Long],
               headers: Map[HeaderKey, HeaderValue] = Map(),
               params: Seq[(String, Any)] = Seq()): EsaResponse =
    postNumber match {
      case Some(pn) =>
        esaClient.sendGet(s"/v1/teams/$currentTeam/posts/$pn/comments",
                          headers,
                          params)
      case None =>
        esaClient.sendGet(s"/v1/teams/$currentTeam/comments", headers, params)
    }

  def createComment(postNumber: Long,
                    headers: Map[HeaderKey, HeaderValue] = Map(),
                    params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendPost(s"/v1/teams/$currentTeam/posts/$postNumber/comments",
                       headers,
                       params)

  // TODO updateComment

  def deleteComment(commentId: Long,
                    headers: Map[HeaderKey, HeaderValue] = Map(),
                    params: Seq[(String, Any)] = Seq()): EsaResponse =
    esaClient.sendDelete(s"/v1/teams/$currentTeam/comments/$commentId",
                         headers,
                         params)

  def createSharing(postNumber: Long,
                    headers: Map[HeaderKey, HeaderValue],
                    params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendPost(s"/v1/teams/$currentTeam/posts/$postNumber/sharing",
                       headers,
                       params)

  def deleteSharing(postNumber: Long,
                    headers: Map[HeaderKey, HeaderValue],
                    params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendDelete(s"/v1/teams/$currentTeam/posts/$postNumber/sharing",
                         headers,
                         params)

  def postStargazers(postNumber: Long,
                     headers: Map[HeaderKey, HeaderValue],
                     params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam/posts/$postNumber/start",
                      headers,
                      params)

  def addPostStar(postNumber: Long,
                  headers: Map[HeaderKey, HeaderValue],
                  params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendPost(s"/v1/teams/$currentTeam/posts/$postNumber/start",
                       headers,
                       params)

  def deletePostStar(postNumber: Long,
                     headers: Map[HeaderKey, HeaderValue],
                     params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendDelete(s"/v1/tams/$currentTeam/posts/$postNumber/star",
                         headers,
                         params)

  def commentStargazers(commentId: Long,
                        headers: Map[HeaderKey, HeaderValue],
                        params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam/comments/$commentId/stargazers",
                      headers,
                      params)

  def addCommentStar(commentId: Long,
                     headers: Map[HeaderKey, HeaderValue],
                     params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendPost(s"/v1/teams/$currentTeam/comments/$commentId/star",
                       headers,
                       params)

  def deleteCommentStar(commentId: Long,
                        headers: Map[HeaderKey, HeaderValue],
                        params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendDelete(s"/v1/teams/$currentTeam/comments/$commentId/star",
                         headers,
                         params)

  def watchers(postNumber: Long,
               headers: Map[HeaderKey, HeaderValue],
               params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendGet(s"/v1/teams/$currentTeam/posts/$postNumber/watch",
                      headers,
                      params)

  def addWatch(postNumber: Long,
               headers: Map[HeaderKey, HeaderValue],
               params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendPost(s"/v1/teams/$currentTeam/posts/$postNumber/watch",
                       headers,
                       params)

  def deleteWatch(postNumber: Long,
                  headers: Map[HeaderKey, HeaderValue],
                  params: Seq[(String, Any)]): EsaResponse =
    esaClient.sendDelete(s"/v1/teams/$currentTeam/posts/$postNumber/watch",
                         headers,
                         params)
}
